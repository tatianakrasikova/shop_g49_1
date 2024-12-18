## Рекомендации по правилам именования эндпоинтов

### Общепринятые правила именования эндпоинтов:

### <span style="color: green;">1. Использовать существительные. <span style="color: red;">НЕ использовать глаголы, обозначающие CRUD-операции.</span></span>

Когда вы проектируете RESTful API, глаголы в именах эндпоинтов заменяются HTTP методами (GET, POST, PUT, DELETE). Это позволяет сделать API более чистым и логичным.

### Плохие примеры:
- http://api.example.com/v1/store/CreateItems/{item-id} ❌
- http://api.example.com/v1/store/getEmployees/{emp-id} ❌
- http://api.example.com/v1/store/update-prices/{price-id} ❌
- http://api.example.com/v1/store/deleteOrders/{order-id} ❌

### Хорошие примеры:
- http://api.example.com/v1/store/items/{item-id} ✅
- http://api.example.com/v1/store/employees/{emp-id} ✅
- http://api.example.com/v1/store/prices/{price-id} ✅
- http://api.example.com/v1/store/orders/{order-id} ✅

### Допустимые случаи использования глаголов:

Глаголы могут быть использованы в именах эндпоинтов, если они не связаны с CRUD-операциями и обозначают специфическое действие. Например:
- **http://api.example.com/v1/store/products/{product-id}/activate** - для активации продукта.
- **http://api.example.com/v1/store/products/{product-id}/deactivate** - для деактивации продукта.
- **http://api.example.com/v1/store/orders/{order-id}/cancel** - для отмены заказа.

---

### <span style="color: green;">2. Использовать имя ресурса во множественном числе. <span style="color: red;">НЕ использовать единственное число, если мы не обращаемся к ресурсу-синглтону.</span></span>

Именование ресурсов во множественном числе помогает четко определить, что эндпоинт обрабатывает коллекцию ресурсов, а не один экземпляр. Исключением могут быть только ресурсы-синглтоны, которые всегда существуют в единственном экземпляре в системе.

### Плохие примеры:
- http://api.example.com/v1/store/item/{item-id} ❌
- http://api.example.com/v1/store/employee/{emp-id}/address ❌

### Хорошие примеры:
- http://api.example.com/v1/store/items/{item-id} ✅
- http://api.example.com/v1/store/employees/{emp-id}/address ✅

### Допустимые случаи использования единственного числа:

Единственное число может использоваться для ресурсов-синглтонов, которые существуют в системе в единственном экземпляре. Например:
- **http://api.example.com/v1/store/config** - для конфигурации системы.
- **http://api.example.com/v1/store/status** - для статуса системы.

---

### <span style="color: green;">3. Использовать дефис. <span style="color: red;">НЕ использовать camelCase, слитное написание слов и символ подчёркивания в именах ресурсов.</span></span>

### Плохие примеры:
- http://api.example.com/v1/store/vendormanagement/{vendor-id} ❌
- http://api.example.com/v1/store/itemmanagement/{item-id}/producttype ❌
- http://api.example.com/v1/store/inventory_management ❌

### Хорошие примеры:
- http://api.example.com/v1/store/vendor-management/{vendor-id} ✅
- http://api.example.com/v1/store/item-management/{item-id}/product-type ✅
- http://api.example.com/v1/store/inventory-management ✅

---


### <span style="color: green;">4. Добавить версии API. <span style="color: red;">НЕ использовать версию API в конце URL.</span></span>

Версионность API позволяет управлять изменениями и обновлениями без нарушения существующих пользователей. Использование версии в начале URL делает его более читаемым и логичным.

### Плохие примеры:
- http://api.example.com/products/v1 ❌
- http://api.example.com/orders/v2 ❌

### Хорошие примеры:
- http://api.example.com/v1/products ✅
- http://api.example.com/v2/orders ✅

---

### <span style="color: green;">5. Иерархия ресурсов. <span style="color: red;">НЕ использовать плоскую структуру для вложенных объектов.</span></span>

Строите иерархию ресурсов для вложенных объектов. Это помогает четко определить отношения между ресурсами и делает API более логичным и структурированным.

### Плохие примеры:
- http://api.example.com/v1/reviews?product-id=123 ❌
- http://api.example.com/v1/items/456/comments ❌

### Хорошие примеры:
- http://api.example.com/v1/products/{product-id}/reviews ✅
- http://api.example.com/v1/items/{item-id}/comments ✅

---

### <span style="color: green;">6. Обработка ошибок. <span style="color: red;">НЕ возвращать ошибки без кода состояния HTTP.</span></span>

Правильная обработка ошибок позволяет пользователям API понять, что пошло не так, и как это исправить. Возвращайте соответствующие коды состояния HTTP и сообщения об ошибках, чтобы сделать API более предсказуемым и удобным для использования.

### Примеры сообщений об ошибках:
- **400 Bad Request:** `{"error": "Invalid request data"}` ✅
- **401 Unauthorized:** `{"error": "Unauthorized access"}` ✅
- **404 Not Found:** `{"error": "Resource not found"}` ✅
- **500 Internal Server Error:** `{"error": "Internal server error"}` ✅

---

### <span style="color: green;">7. Использовать параметры для поиска, фильтрации, сортировки. <span style="color: red;">НЕ использовать переменные пути для поиска, фильтрации, сортировки.</span></span>

Для улучшения читаемости и понимания эндпоинтов используйте параметры запроса для поиска, фильтрации и сортировки данных. Это делает запросы более гибкими и легкими для интерпретации.

### Плохие примеры:
- `/getUsers/2/10` ❌
- `/listProducts/sortByPrice` ❌

### Хорошие примеры:
- `/users?page=2&perPage=10` ✅
- `/products?sort=price` ✅

### Дополнительные примеры:

Используйте параметры для более сложных запросов:
- **Фильтрация по нескольким параметрам:** `/products?category=electronics&brand=apple`
- **Сортировка и пагинация:** `/products?sort=price&order=asc&page=1&perPage=20`

Это позволяет легко расширять и изменять запросы без необходимости менять структуру URL.

---

### <span style="color: green;">8. Использовать осмысленные имена параметров и переменных пути. <span style="color: red;">НЕ использовать сокращённые и не несущие смысла имена параметров.</span></span>

### Плохие примеры:
- `/users?q=John` ❌
- `/books?search=history` ❌

### Хорошие примеры:
- `/users?name=John` ✅
- `/books?category=history` ✅

---

### <span style="color: green;">9. Упускайте расширения файлов. <span style="color: red;">НЕ использовать расширения имён файлов.</span></span>

### Плохие примеры:
- http://api.example.com/v1/store/items.json ❌
- http://api.example.com/v1/store/products.xml ❌

### Хорошие примеры:
- http://api.example.com/v1/store/items ✅
- http://api.example.com/v1/store/products ✅

---

## <span style="color: green;">10. Соблюдать единообразие. <span style="color: red;">НЕ использовать разные форматы для ответа.</span></span>

---
