import AddBovineInputForm from '@/components/AddBovineInputForm'
import React from 'react'

const bovines = () => {
  return (
    <div className="flex align-middle items-center justify-center mt-5">
      <div className="flex justify-center align-middle bg-green-700 rounded-xl p-2 ">
        <AddBovineInputForm />
      </div>
    </div>
    
  )
}

export default bovines