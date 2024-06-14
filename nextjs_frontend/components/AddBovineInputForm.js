"use client";

import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { z } from "zod";

import { Button } from "@/components/ui/button";
import {
  Form,
  FormControl,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from "@/components/ui/form";
import { RadioGroup, RadioGroupItem } from "./ui/radio-group";
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "./ui/select";
import { Input } from "@/components/ui/input";
import { toast } from "@/components/ui/use-toast";

const FormSchema = z.object({
  bovineCode: z
    .string()
    .min(3, {
      message: "O codigo bovino tem de ter pelo menos 3 caracteres.",
    })
    .startsWith(
      "PT" || "ES",
      "O código bovino tem de comecar com 'PT' ou 'ES'."
    ),
  bovineBreed: z.string(),
  bovineGender: z.string(),
  bovineColor: z.string(),
});

export default function AddBovineInputForm() {
  const form = useForm({
    resolver: zodResolver(FormSchema),
    defaultValues: {
      bovineCode: "",
      bovineBreed: "",
      bovineColor: "",
      bovineGender: "",
      bovineBirthDate: "",
      bovineStatus: "VIVO",
      bovineName: "",
      mothersCode: "",
      fathersCode: "",
      lastKnownOwnerNif: "",
    },
  });

  function onSubmit(data) {
    console.log(data);
    toast({
      title: "You submitted the following values:",
      description: (
        <pre className="mt-2 w-[340px] rounded-md bg-slate-950 p-4">
          <code className="text-white">{JSON.stringify(data, null, 2)}</code>
        </pre>
      ),
    });
  }

  return (
    <Form {...form}>
      <form onSubmit={form.handleSubmit(onSubmit)} className="w-3/4 space-y-6">
        <div className="flex flex-row space-x-3">
          <div className="flex flex-col">
            <FormField
              control={form.control}
              name="bovineCode"
              render={({ field }) => (
                <FormItem>
                  <FormLabel className="text-white">Codigo do bovino</FormLabel>
                  <FormControl>
                    <Input placeholder="PT..." {...field} />
                  </FormControl>
                </FormItem>
              )}
            />

            <FormField
              control={form.control}
              name="bovineBreed"
              render={({ field }) => (
                <FormItem>
                  <FormLabel className="text-white">Raca</FormLabel>
                  <Select
                    onValueChange={field.onChange}
                    defaultValue={field.value}
                  >
                    <FormControl>
                      <SelectTrigger>
                        <SelectValue placeholder="Raca" />
                      </SelectTrigger>
                    </FormControl>
                    <SelectContent>
                      <SelectItem value="ANGUS">Angus</SelectItem>
                      <SelectItem value="CRUZADO_ANGUS">
                        Cruzado de Angus
                      </SelectItem>
                      <SelectItem value="CHAROLES">Charoles</SelectItem>
                    </SelectContent>
                  </Select>
                  <FormMessage />
                </FormItem>
              )}
            />

            <FormField
              control={form.control}
              name="bovineColor"
              render={({ field }) => (
                <FormItem>
                  <FormLabel className="text-white">Cor do Bovino</FormLabel>
                  <Select
                    onValueChange={field.onChange}
                    defaultValue={field.value}
                  >
                    <FormControl>
                      <SelectTrigger>
                        <SelectValue
                          className="text-black"
                          placeholder="Cor do bovino"
                        />
                      </SelectTrigger>
                    </FormControl>
                    <SelectContent>
                      <SelectItem value="PRETO">Preto</SelectItem>
                      <SelectItem value="BRANCO">Branco</SelectItem>
                      <SelectItem value="VERMELHO">Vermelho</SelectItem>
                    </SelectContent>
                  </Select>
                  <FormMessage />
                </FormItem>
              )}
            />

            <FormField
              control={form.control}
              name="bovineGender"
              render={({ field }) => (
                <FormItem className="space-y-3 text-white">
                  <FormLabel>Genero</FormLabel>
                  <FormControl>
                    <RadioGroup
                      onValueChange={field.onChange}
                      defaultValue={field.value}
                      className="flex flex-col space-y-1"
                    >
                      <FormItem className="flex items-center space-x-3 space-y-0">
                        <FormControl>
                          <RadioGroupItem
                            className="text-white"
                            value="MACHO"
                          />
                        </FormControl>
                        <FormLabel className="font-normal">Macho</FormLabel>
                      </FormItem>
                      <FormItem className="flex items-center space-x-3 space-y-0">
                        <FormControl>
                          <RadioGroupItem
                            className="text-white"
                            value="FEMEA"
                          />
                        </FormControl>
                        <FormLabel className="font-normal">Femea</FormLabel>
                      </FormItem>
                    </RadioGroup>
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
          </div>
          <div className="flex flex-col">
              
          </div>
        </div>

        <Button type="submit">Submit</Button>
      </form>
    </Form>
  );
}
