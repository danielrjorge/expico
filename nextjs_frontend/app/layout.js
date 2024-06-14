import "./globals.css";
import { Toaster } from "@/components/ui/toaster";

export const metadata = {
  title: "Expico",
  description: "Gestor de exploracao bovina",
};

export default function RootLayout({ children }) {
  return (
    <html lang="en">
      <body className="bg-gradient-to-r from-green-300 to-green-700">
        <header className="bg-slate-700 text-white border-double border-4 border-white rounded-b-lg sticky flex justify-center text-center items-center p-4">
          <h1 className="font-bold font-mono antialiased text-2xl text-center">Expico - Gestor de exploracao bovina</h1>
        </header>
        <div>
          {children}
        </div>
        <Toaster />
      </body>
    </html>
  );
}
