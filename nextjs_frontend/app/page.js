import GenericImageCard from "@/components/GenericImageCard";

export default function Home() {
  return (
    <main>
      <div className="flex items-center justify-center mt-10 gap-8">
        <GenericImageCard imgSrc="bovines.jpg" link="/bovines" label="Bovinos"/>
        <GenericImageCard imgSrc="owner.jpg" link="/owners" label="Proprietários"/>
        <GenericImageCard imgSrc="land.jpg" link="/land" label="Terrenos"/>
      </div>
    
    </main>
  );
}
