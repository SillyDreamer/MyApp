package com.example.myapp.model

import android.content.Context

class Model(var context : Context) {
    val dbHandler = DBOpenHelper(context, null)


    inner class CallDB : Runnable{
        override fun run() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    fun addToDBProduct(product: Product) {
        dbHandler.add(product)
//        Toast.makeText(context, "Product added to db", Toast.LENGTH_LONG).show()
    }

    fun addToDBUser(name : String, money : Int) {
        dbHandler.addUser(name, money)
  //      Toast.makeText(context, "User added to db", Toast.LENGTH_LONG).show()

    }

    fun addToDBCheck(name: String) {
        dbHandler.addCheck(name)
    }

    fun showCheckId() : Long {
        var id : Long = 0
        val cursor =  dbHandler.getChecks()
        if (cursor != null) {
            while (cursor.moveToNext()) {
                id = cursor.getLong(cursor.getColumnIndex(DBOpenHelper.COLUMN_ID))
            }
        }
        return id
    }

    fun showChecks() : ArrayList<String> {
        val cursor = dbHandler.getChecks()
        val arr = arrayListOf<String>()
        if (cursor != null) {
            while(cursor.moveToNext()) {
                arr.add(cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLUMN_NAME)))
            }
        }
        return arr
    }

    fun showProducts(check_id : Long) : ArrayList<Product> {
        val cursor =  dbHandler.getProducts()
        val arr = arrayListOf<Product>()
        if (cursor != null) {
            while (cursor.moveToNext()) {
                if (cursor.getLong(cursor.getColumnIndex((DBOpenHelper.COLUMN_CHECK_ID))) == check_id) {
                    arr.add(
                        Product(
                            cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLUMN_NAME)),
                            cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLUMN_PRICE)),
                            cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLUMN_COUNT))
                        )
                    )
                }
            }
        }
        return arr
    }

    fun showUsers(check_id : Long) : ArrayList<User> {
        val cursor =  dbHandler.getUsers()
        val arr = arrayListOf<User>()
        if (cursor != null) {
            while (cursor.moveToNext()) {
                if (cursor.getLong(cursor.getColumnIndex(DBOpenHelper.COLUMN_CHECK_ID)) == check_id) {
                    arr.add(
                        User(
                            cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLUMN_NAME)),
                            cursor.getInt(cursor.getColumnIndex(DBOpenHelper.COLUMN_MONEY))
                        )
                    )
                }
            }
        }
        return arr
    }

    fun updateUser(name : String, money : Int, id : Int, check_id : Long) {
        dbHandler.updateUser(name, money, id, check_id)
    }

    fun dropTable() {
        dbHandler.dropTable()
    }
}