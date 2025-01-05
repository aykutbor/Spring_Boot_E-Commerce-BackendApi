## Proje Açıklaması

Bu proje, Java Spring Boot kullanarak geliştirilmiş basit bir e-ticaret backend sistemini temsil eder. Sistem, aşağıdaki ana işlevleri içerir:

- **Ürün Yönetimi**: Ürünlerin listelenmesi, eklenmesi, güncellenmesi, silinmesi ve kategori bazlı sorgulanması.
- **Kullanıcı Yönetimi**: Kullanıcı kaydı, girişi ve profil yönetimi.
- **Sipariş İşlemleri**: Sipariş oluşturma, listeleme ve durum güncellemeleri.
- **JWT Authentication**: Kullanıcıların güvenli bir şekilde sisteme giriş yapabilmesi için JWT tabanlı kimlik doğrulama.

## Kullanılan Teknolojiler:
- **Java**
- **Spring Boot**
- **PostgreSQL**
- **Docker**
- **Spring Security (JWT Implementasyonu)**

## Teknik Gereksinimler

Proje aşağıdaki teknolojileri kullanarak geliştirilmiştir:
- **Java**: Projeyi geliştirmek için kullanılan ana programlama dili.
- **Spring Boot**: Uygulamanın ana çerçevesi.
- **PostgreSQL**: Veritabanı yönetim sistemi.
- **Docker**: Uygulamanın taşınabilirliğini sağlamak için Docker kullanılmıştır.
- **Spring Security**: JWT tabanlı kimlik doğrulama ve rol tabanlı yetkilendirme.
- **Swagger**: API dokümantasyonu için Swagger kullanılmıştır.

- ## API Endpoints

### Kullanıcı İşlemleri
- **POST** `/api/auth/login`: Kullanıcı girişi yap (E-posta ve şifre ile)
- **POST** `/api/auth/register`: Yeni kullanıcı kaydı oluştur
- **GET** `/api/auth/{userId}/profile`: Kullanıcı profil bilgilerini getir
- **PUT** `/api/auth/{userId}/profile`: Kullanıcı profil bilgilerini güncelle
- **POST** `/api/auth/{userId}/address`: Kullanıcıya yeni adres ekle
---
### Ürün İşlemleri
- **GET** `/api/v1/products`: Tüm ürünleri listele
- **GET** `/api/v1/products/{id}`: Ürün bilgilerini ID ile getir
- **POST** `/api/v1/products`: Yeni ürün oluştur
- **PUT** `/api/v1/products/{id}`: Mevcut ürün bilgilerini güncelle
- **DELETE** `/api/v1/products/{id}`: Ürünü sil
- **PUT** `/api/v1/products/{id}/stock`: Ürün stok bilgilerini güncelle
---
### Sipariş İşlemleri
- **POST** `/api/orders`: Yeni sipariş oluştur
- **GET** `/api/orders/{id}`: Sipariş bilgilerini ID ile getir
- **GET** `/api/orders/user/{userId}`: Kullanıcının tüm siparişlerini listele
- **PUT** `/api/orders/{id}/status`: Sipariş durumunu güncelle
- **DELETE** `/api/orders/{id}`: Siparişi iptal et


## Projenin Kurulumu

Proje aşağıdaki adımları takip ederek kurulabilir ve çalıştırılabilir:

1. **Gerekli Bağımlılıkları Yükleyin**:
    - Java 17 veya üzeri
    - PostgreSQL veritabanı
    - Docker (Opsiyonel)

2. **Proje Kodlarını İndirin**:
    ```bash
    git clone https://github.com/aykutbor/Spring_Boot_E-Commerce-BackendApi.git
    ```

3. **PostgreSQL Veritabanını Yapılandırın**:
    Veritabanı bağlantı bilgilerini `application.properties` dosyasından ayarlayın.

4. **Projeyi Çalıştırın**:
    ```bash
    ./mvnw spring-boot:run
    ```

Proje, başarıyla çalışmaya başladıktan sonra API endpoint'lerine istek gönderebilirsiniz.

## Veritabanı Şeması

Veritabanında aşağıdaki tablolar bulunmaktadır:
- **users**: Kullanıcı bilgilerini tutar (id, name, email, password, role, vs.).
- **products**: Ürün bilgilerini tutar (id, name, description, price, stock, category, vs.).
- **orders**: Sipariş bilgilerini tutar (id, user_id, product_id, status, order_date, vs.).
- **addresses**: Kullanıcı adres bilgilerini tutar (id, user_id, address, city, country, vs.).

## Docker Kullanımı

Proje Docker kullanarak kolayca çalıştırılabilir. Aşağıdaki adımları takip edebilirsiniz:

1. **Dockerfile'ı ve Docker Compose Dosyasını Kullanarak Projeyi Çalıştırın**:
    ```bash
    docker-compose up --build
    ```
    **Postgres ile Docker Konteyneri Oluşturma Komutu**:
     ```bash
    docker run --name my-postgres -e POSTGRES_USER=myuser -e POSTGRES_PASSWORD=mypassword -e POSTGRES_DB=mydatabase -p 5432:5432 -d postgres
    ```

2. Proje çalıştığında, API'ye `localhost:8080` üzerinden erişebilirsiniz.





  

