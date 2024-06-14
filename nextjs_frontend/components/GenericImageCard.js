import * as React from "react"

import {
  Card,
  CardContent,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card"

export default function GenericImageCard( {imgSrc, link, label} ) {
  return (
    <a href={link}>
        <Card className="w-[350px] border-2 bg-slate-400 hover:bg-white">
            <img className=" rounded-xl p-1" src={imgSrc} />
        <CardFooter className=" mt-4 justify-center">
            <h1 className="p-1 rounded font-sans font-semibold text-2xl">{label}</h1>
        </CardFooter>
        </Card>
    </a>
  )
}
