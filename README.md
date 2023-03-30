
# reza.mr_testProject


## Step by Step Instalasi

1. Eksekusi ```db_padepokan_79.sql``` menggunakan SQl Server Management Studio atau Azure Data Studio.

2. Buka file ```path-to/reza.mr_testProject/src/main/resource/application.properties```. Kemudian sesuaikan username, password dan url database.
    ```spring.datasource.url=jdbc:sqlserver://URL_DB_KAMU:PORT_DB_KAMU;encrypt=true;trustServerCertificate=true;databaseName=Padepokan_79```

    ```spring.datasource.username=(Username)```

    ```spring.datasource.password=(Password)```

3. Jalankan perintah berikut pada ``terminal`` atau ```cmd```

    ```mnv spring-boot:run```

4. Tunggu proses hingga selesai, kemudian buka aplikasi postman lalu import file ```api_document.postman_collection.json```

5. Jalanakan salah satu request postman yang telah di import untuk melakukan pengjuan pada aplikasi.