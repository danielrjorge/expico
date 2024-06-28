"use client"

import AddBovineInputForm from '@/components/AddBovineInputForm'
import React, { useState, useEffect } from 'react'
import { TooltipProvider } from '@radix-ui/react-tooltip'
import Link from 'next/link'
import { Package, BookOpenText, LineChart, Settings, Euro, Tractor, Users2, Plus, Percent } from 'lucide-react'
import { Tooltip, TooltipTrigger, TooltipContent } from '@/components/ui/tooltip'
import Image from "next/image"
import { MoreHorizontal } from "lucide-react"

import { Badge } from "@/components/ui/badge"
import { Button } from "@/components/ui/button"
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card"
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuLabel,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu"
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table"
import { getAllBovines, getAllFarmBovines, getAllOwners, addBovineSale } from '@/services/api/api'
import { Popover, PopoverTrigger, PopoverContent } from '@/components/ui/popover'
import { Label } from '@/components/ui/label'
import { Input } from '@/components/ui/input'
import { PopoverClose } from '@radix-ui/react-popover'
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select"
import { ToastAction } from "@/components/ui/toast"
import { useToast } from "@/components/ui/use-toast"


const bovines = () => {

  const [showAddBovineForm, setShowAddBovineForm] = useState(false);
  const [showAllBovinesCard, setShowAllBovinesCard] = useState(false);
  const [showFarmBovinesCard, setShowFarmBovinesCard] = useState(false);
  const [allBovinesList, setAllBovinesList] = useState([]);
  const [farmBovinesList, setFarmBovinesList] = useState([]);
  const [sellBovineData, setSellBovineData] = useState("");
  const [allOwnersList, setAllOwnersList] = useState("");

  const { toast } = useToast()

  useEffect(() => {
    handleGetAllOwners();
  }, []);

  function displayAddBovineForm() {
    setShowFarmBovinesCard(false);
    setShowAllBovinesCard(false);
    setShowAddBovineForm(true);
  }

  function displayAllBovinesCard() {
    handleGetAllBovines();
    setShowAddBovineForm(false);
    setShowFarmBovinesCard(false);
    setShowAllBovinesCard(true);
  }

  function displayFarmBovinesCard() {
    handleGetFarmBovines();
    setShowAddBovineForm(false);
    setShowAllBovinesCard(false);
    setShowFarmBovinesCard(true);
  }

  function handleGetAllBovines() {
    // GET request using axios with error handling
    getAllBovines()
      .then(response => {
        setAllBovinesList(response.data);
        console.log(response.data);
      })
      .catch(error => {
        console.error('There was an error!', error);
      });
  }

  function handleGetFarmBovines() {
    getAllFarmBovines()
      .then(response => {
        setFarmBovinesList(response.data);
        console.log(response.data);
      })
      .catch(error => {
        console.error('There was an error!', error);
      });
  }

  function handleGetAllOwners() {
    getAllOwners()
      .then(response => {
        //removes self (assuming profile owner is in the first position)
        response.data.splice(0,1);
        setAllOwnersList(response.data);
        console.log(response.data);
      })
      .catch(error => {
        console.error('There was an error!', error);
      });

  }

  function chooseStatusBadgeStyle(status) {
    switch (status) {
      case "VIVO":
        return "";
      case "VENDIDO":
        return "secondary";
      case "MORTO", "ABATIDO":
        return "destructive"
    }
  }

  function chooseImageFromBreed(breed) {
    switch (breed) {
      case "ANGUS":
        return "/bovines.jpg"
      case "CHAROLES":
        return "/charolais.jpg"
      case "CRUZADO_ANGUS":
        return "/angus_mix.jpg"
    }
  }

  // TODO consider doing this on server side
  function roundPrice(price) {
    return parseFloat(price).toFixed(2);
  }

  function submitSale() {
    //check if all the fields are filled
    if(sellBovineData.saleDate == null || sellBovineData.buyer == null || sellBovineData.totalPrice == null || sellBovineData.vatPercentage == null) {
      toast({
        title: "Erro",
        description: "Um ou mais dos campos da venda nao foram preenchidos",
        action: (
          <ToastAction altText="Por favor preencher todos os campos da venda">Voltar</ToastAction>
        ),
      })
      return;
    }

    addBovineSale(sellBovineData)
      .then(response => {
        toast({
          title: "Successo",
          description: "O bovino com codigo " + sellBovineData.bovineCode + " foi vendido ao proprietario " + sellBovineData.buyer.ownerName,
          action: (
            <ToastAction altText="Fechar notificacao">Voltar</ToastAction>
          ),
        })
        console.log(response.data);
      })
      .catch(error => {
        toast({
          title: "Erro",
          description: "Ocorreu um erro ao submeter o seu pedido, contacte o administrador",
        })
        console.error('There was an error!', error);
      });

  }

  return (
    <div className="flex">
      <TooltipProvider>
        <div id='bovineSidebar' className='flex bg-blue-300'>
          <aside className=" inset-y-0 left-0 z-10 hidden w-14 flex-col border-r bg-background sm:flex">
            <nav className="flex flex-col items-center gap-4 px-2 sm:py-5">
              <Link
                href="#"
                onClick={displayAddBovineForm}
                className="group flex h-9 w-9 shrink-0 items-center justify-center gap-2 rounded-full bg-primary text-lg font-semibold text-primary-foreground md:h-8 md:w-8 md:text-base"
              >
                <Plus className="h-4 w-4 transition-all group-hover:scale-110" />
                <span className="sr-only">Acme Inc</span>
              </Link>
              <Tooltip>
                <TooltipTrigger asChild>
                  <Link
                    href="#"
                    onClick={displayAllBovinesCard}
                    className="flex h-9 w-9 items-center justify-center rounded-lg text-muted-foreground transition-colors hover:text-foreground md:h-8 md:w-8"
                  >
                    <BookOpenText className="h-5 w-5" />
                    <span className="sr-only">Todos os bovinos</span>
                  </Link>
                </TooltipTrigger>
                <TooltipContent side="right">Todos os bovinos</TooltipContent>
              </Tooltip>
              <Tooltip>
                <TooltipTrigger asChild>
                  <Link
                    href="#"
                    onClick={displayFarmBovinesCard}
                    className="flex h-9 w-9 items-center justify-center rounded-lg text-accent-foreground transition-colors hover:text-foreground md:h-8 md:w-8"
                  >
                    <Tractor className="h-5 w-5" />
                    <span className="sr-only">Bovinos da exploracao</span>
                  </Link>
                </TooltipTrigger>
                <TooltipContent side="right">Bovinos da exploracao</TooltipContent>
              </Tooltip>
              <Tooltip>
                <TooltipTrigger asChild>
                  <Link
                    href="#"
                    className="flex h-9 w-9 items-center justify-center rounded-lg text-muted-foreground transition-colors hover:text-foreground md:h-8 md:w-8"
                  >
                    <Package className="h-5 w-5" />
                    <span className="sr-only">Products</span>
                  </Link>
                </TooltipTrigger>
                <TooltipContent side="right">Products</TooltipContent>
              </Tooltip>
              <Tooltip>
                <TooltipTrigger asChild>
                  <Link
                    href="#"
                    className="flex h-9 w-9 items-center justify-center rounded-lg text-muted-foreground transition-colors hover:text-foreground md:h-8 md:w-8"
                  >
                    <Users2 className="h-5 w-5" />
                    <span className="sr-only">Customers</span>
                  </Link>
                </TooltipTrigger>
                <TooltipContent side="right">Customers</TooltipContent>
              </Tooltip>
              <Tooltip>
                <TooltipTrigger asChild>
                  <Link
                    href="#"
                    className="flex h-9 w-9 items-center justify-center rounded-lg text-muted-foreground transition-colors hover:text-foreground md:h-8 md:w-8"
                  >
                    <LineChart className="h-5 w-5" />
                    <span className="sr-only">Analytics</span>
                  </Link>
                </TooltipTrigger>
                <TooltipContent side="right">Analytics</TooltipContent>
              </Tooltip>
            </nav>
            <nav className="mt-auto flex flex-col items-center gap-4 px-2 sm:py-5">
              <Tooltip>
                <TooltipTrigger asChild>
                  <Link
                    href="#"
                    className="flex h-9 w-9 items-center justify-center rounded-lg text-muted-foreground transition-colors hover:text-foreground md:h-8 md:w-8"
                  >
                    <Settings className="h-5 w-5" />
                    <span className="sr-only">Settings</span>
                  </Link>
                </TooltipTrigger>
                <TooltipContent side="right">Settings</TooltipContent>
              </Tooltip>
            </nav>
          </aside>
        </div>
      </TooltipProvider>
      <div className="flex flex-col mt-5 ml-5 justify-center align-middle bg-green-700 rounded-xl p-2 ">
        {showAddBovineForm && (<AddBovineInputForm id="addBovineForm" />)}

        <Card id='GetAllBovinesCardList' hidden={!showAllBovinesCard}>
          <CardHeader>
            <CardTitle>Existencias (Bovinos)</CardTitle>
            <CardDescription>
              Todos os bovinos registados nesta plataforma
            </CardDescription>
          </CardHeader>
          <CardContent>
            <Table>
              <TableHeader>
                <TableRow>
                  <TableHead className="hidden w-[100px] sm:table-cell">
                    <span className="sr-only">Image</span>
                  </TableHead>
                  <TableHead>Código Bovino</TableHead>
                  <TableHead>Estado</TableHead>
                  <TableHead className="hidden md:table-cell">Genero</TableHead>
                  <TableHead className="hidden md:table-cell">
                    Raca
                  </TableHead>
                  <TableHead className="hidden md:table-cell">Data de Nascimento</TableHead>
                  <TableHead className="hidden md:table-cell">Nome</TableHead>
                  <TableHead className="hidden md:table-cell">Codigo da Mae</TableHead>
                  <TableHead className="hidden md:table-cell">Codigo do Pai</TableHead>
                  <TableHead className="hidden md:table-cell">Ultimo Proprietario Conhecido</TableHead>
                  <TableHead>
                    <span className="sr-only">Actions</span>
                  </TableHead>
                </TableRow>
              </TableHeader>
              <TableBody>
                {allBovinesList.map((row) => (
                  <TableRow key={row.bovineInternalId}>
                    <TableCell className="hidden sm:table-cell">
                      <Image
                        alt="Product image"
                        className="aspect-square rounded-md object-cover"
                        height="64"
                        src={chooseImageFromBreed(row.bovineBreed)}
                        width="64"
                      />
                    </TableCell>
                    <TableCell className="font-medium">
                      {row.bovineCode}
                    </TableCell>
                    <TableCell>
                      <Badge variant={chooseStatusBadgeStyle(row.bovineStatus)}>{row.bovineStatus}</Badge>
                    </TableCell>
                    <TableCell className="hidden md:table-cell">{row.bovineGender}</TableCell>
                    <TableCell className="hidden md:table-cell">{row.bovineBreed}</TableCell>
                    <TableCell className="hidden md:table-cell">
                      {row.bovineBirthDate}
                    </TableCell>
                    <TableCell className="hidden md:table-cell">
                      {row.bovineName}
                    </TableCell>
                    <TableCell className="hidden md:table-cell">
                      {row.mothersCode}
                    </TableCell>
                    <TableCell className="hidden md:table-cell">
                      {row.fathersCode}
                    </TableCell>
                    <TableCell className="hidden md:table-cell">
                    <Popover>
                        <PopoverTrigger asChild>
                          <Button
                            variant="ghost"
                            >
                            {row.lastKnownOwner.ownerName}
                          </Button>
                        </PopoverTrigger>
                        <PopoverContent className="w-80">
                          <div className="grid gap-4">
                            <div className="space-y-2">
                              <h4 className="font-medium leading-none">Dados do proprietario</h4>
                            </div>
                            <div className="grid gap-2">
                              <div className="grid grid-cols-4 items-center gap-4">
                                <Label className='col-span-1'>Nome</Label>
                                <p className='col-span-3'>{row.lastKnownOwner.ownerName}</p>
                              </div>
                              <div className="grid grid-cols-4 items-center gap-4">
                                <Label className='col-span-1'>NIF</Label>
                                <p className='col-span-3'>{row.lastKnownOwner.ownerNIF}</p>
                              </div>
                              <div className="grid grid-cols-4 items-center gap-4">
                                <Label className='col-span-1'>Telemovel</Label>
                                <p className='col-span-3'>{row.lastKnownOwner.ownerCellNumber}</p>
                              </div>
                              <div className="grid grid-cols-4 items-center gap-4">
                                <Label className='col-span-1'>Email</Label>
                                <p className='col-span-3'>{row.lastKnownOwner.ownerEmail}</p>
                              </div>
                            </div>
                          </div>
                        </PopoverContent>
                      </Popover>
                    </TableCell>
                    <TableCell>
                      <DropdownMenu>
                        <DropdownMenuTrigger asChild>
                          <Button aria-haspopup="true" size="icon" variant="ghost">
                            <MoreHorizontal className="h-4 w-4" />
                            <span className="sr-only">Toggle menu</span>
                          </Button>
                        </DropdownMenuTrigger>
                        <DropdownMenuContent align="end">
                          <DropdownMenuLabel>Actions</DropdownMenuLabel>
                          <DropdownMenuItem>Edit</DropdownMenuItem>
                          <DropdownMenuItem>Delete</DropdownMenuItem>
                        </DropdownMenuContent>
                      </DropdownMenu>
                    </TableCell>
                  </TableRow>))}
              </TableBody>
            </Table>
          </CardContent>
          <CardFooter>

          </CardFooter>
        </Card>

        <Card id='GetFarmBovinesCardList' hidden={!showFarmBovinesCard}>
          <CardHeader>
            <CardTitle>Bovinos da exploracao</CardTitle>
            <CardDescription>
              Todos os bovinos existentes na exploracao
            </CardDescription>
          </CardHeader>
          <CardContent>
            <Table>
              <TableHeader>
                <TableRow>
                  <TableHead className="hidden w-[100px] sm:table-cell">
                    <span className="sr-only">Image</span>
                  </TableHead>
                  <TableHead>Código Bovino</TableHead>
                  <TableHead className="hidden md:table-cell">Genero</TableHead>
                  <TableHead className="hidden md:table-cell">
                    Raca
                  </TableHead>
                  <TableHead className="hidden md:table-cell">Data de Nascimento</TableHead>
                  <TableHead className="hidden md:table-cell">Nome</TableHead>
                  <TableHead className="hidden md:table-cell">Codigo da Mae</TableHead>
                  <TableHead className="hidden md:table-cell">Codigo do Pai</TableHead>
                  <TableHead>
                    <span className="sr-only">Actions</span>
                  </TableHead>
                </TableRow>
              </TableHeader>
              <TableBody>
                {farmBovinesList.map((row) => (
                  <TableRow key={row.bovine.bovineInternalId}>
                    <TableCell className="hidden sm:table-cell">
                      <Image
                        alt="Product image"
                        className="aspect-square rounded-md object-cover"
                        height="64"
                        src={chooseImageFromBreed(row.bovine.bovineBreed)}
                        width="64"
                      />
                    </TableCell>
                    <TableCell className="font-medium">
                      {row.bovine.bovineCode}
                    </TableCell>
                    <TableCell className="hidden md:table-cell">{row.bovine.bovineGender}</TableCell>
                    <TableCell className="hidden md:table-cell">{row.bovine.bovineBreed}</TableCell>
                    <TableCell className="hidden md:table-cell">
                      {row.bovine.bovineBirthDate}
                    </TableCell>
                    <TableCell className="hidden md:table-cell">
                      {row.bovine.bovineName}
                    </TableCell>
                    <TableCell className="hidden md:table-cell">
                      {row.bovine.mothersCode}
                    </TableCell>
                    <TableCell className="hidden md:table-cell">
                      {row.bovine.fathersCode}
                    </TableCell>
                    <TableCell>
                      <Popover>
                        <PopoverTrigger asChild>
                          <Button
                            variant="outline"
                            onClick={() => setSellBovineData(prevState => ({
                              ...prevState,
                              'bovineCode': row.bovine.bovineCode
                            }))}>
                            Vender
                          </Button>
                        </PopoverTrigger>
                        <PopoverContent className="w-80">
                          <div className="grid gap-4">
                            <div className="space-y-2">
                              <h4 className="font-medium leading-none">Venda de Bovino</h4>
                              <p className="text-sm text-muted-foreground">
                                Por favor insira os dados da venda do animal
                              </p>
                            </div>
                            <div className="grid gap-2">
                              <div className="grid grid-cols-3 items-center gap-4">
                                <Label htmlFor="dateOfSale">Data da venda</Label>
                                <Input
                                  id="dateOfSale"
                                  type="date"
                                  className="col-span-2 h-8"
                                  onChange={(e) => setSellBovineData(prevState => ({
                                    ...prevState,
                                    'saleDate': e.target.value
                                  }))}
                                />
                              </div>
                              <div className="grid grid-cols-3 items-center gap-4">
                                <Label htmlFor="newOwner">Novo Proprietario</Label>
                                <Select onValueChange={(e) => setSellBovineData(prevState => ({
                                    ...prevState,
                                    'buyer': e
                                  }))}
                                  >
                                  <SelectTrigger className="col-span-2 h-8">
                                    <SelectValue />
                                  </SelectTrigger>
                                  <SelectContent >
                                    {allOwnersList.map((item) => ( <SelectItem key={item.ownerNIF} 
                                    value={item}>{item.ownerName}</SelectItem>))}
                                  </SelectContent>
                                </Select>
                              </div>
                              <div className="grid grid-cols-3 items-center gap-4">
                                <Label htmlFor="totalPrice">Preco</Label>
                                <div className='col-span-2 grid grid-cols-8 items-center'>
                                <Input
                                  id="totalPrice"
                                  className="col-span-7 h-8"
                                  type="number"
                                  step=".01"
                                  min="0.00"
                                  onChange={(e) => setSellBovineData(prevState => ({
                                    ...prevState,
                                    'totalPrice': roundPrice(e.target.value)
                                  }))}
                                />
                                <Euro className='h-4 w-4 col-span-1'/>
                                </div>
                                
                              </div>
                              <div className="grid grid-cols-3 items-center gap-4">
                                <Label htmlFor="vat">Taxa IVA</Label>
                                <div className='col-span-2 grid grid-cols-8 items-center'>
                                <Input
                                  id="vat"
                                  className="col-span-7 h-8 text-center"
                                  type="number"
                                  step="1"
                                  min="0"
                                  onChange={(e) => setSellBovineData(prevState => ({
                                    ...prevState,
                                    'vatPercentage': e.target.value
                                  }))}
                                />
                                <Percent className='h-4 w-4 col-span-1'/>
                                </div>
                              </div>
                              <div className="grid grid-cols-2 items-center justify-between gap-4">
                                <Button onClick={submitSale}>Submeter</Button>
                                <PopoverClose className='bg-red-500 p-2 rounded text-white'
                                  onClick={() => setSellBovineData("")}>Cancelar</PopoverClose>

                              </div>
                            </div>
                          </div>
                        </PopoverContent>
                      </Popover>
                    </TableCell>
                  </TableRow>))}
              </TableBody>
            </Table>
          </CardContent>
          <CardFooter>

          </CardFooter>
        </Card>
      </div>
    </div>

  )
}

export default bovines