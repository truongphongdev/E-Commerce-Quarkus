-- Master Data
INSERT INTO roles (id, name, description, created_at, updated_at) VALUES
(1, 'ADMIN', 'Quản trị viên hệ thống', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'USER', 'Người dùng thông thường', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO categories (id, name, slug, description, parent_id, created_at, updated_at) VALUES
(1, 'Điện thoại', 'dien-thoai', 'Các loại điện thoại thông minh', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'Laptop', 'laptop', 'Máy tính xách tay các hãng', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 'Phụ kiện', 'phu-kien', 'Phụ kiện điện thoại và laptop', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 'Apple', 'apple', 'Sản phẩm Apple', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, 'Samsung', 'samsung', 'Sản phẩm Samsung', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(6, 'Thời trang nam', 'thoi-trang-nam', 'Quần áo, phụ kiện dành cho nam giới', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(7, 'Thời trang nữ', 'thoi-trang-nu', 'Quần áo, phụ kiện dành cho nữ giới', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(8, 'Áo thun nam', 'ao-thun-nam', 'Áo thun nam các loại', 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(9, 'Quần Jeans nam', 'quan-jeans-nam', 'Quần jeans dài, short nam', 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(10, 'Váy nữ', 'vay-nu', 'Váy dự tiệc, váy dạo phố', 7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO suppliers (id, name, contact_name, email, phone, address) VALUES
(1, 'Công ty TNHH Apple Việt Nam', 'Tim Cook', 'contact@apple.vn', '0901234567', 'Quận 1, TP HCM'),
(2, 'Samsung Electronics Vietnam', 'Lee Jae Yong', 'contact@samsung.vn', '0909876543', 'Khu công nghệ cao, TP Thủ Đức'),
(3, 'Zara Vietnam', 'Trần Zara', 'contact@zara.vn', '0911222333', 'Vincom Đồng Khởi, Quận 1, TP HCM'),
(4, 'H&M Vietnam', 'Nguyễn H&M', 'contact@hm.vn', '0922333444', 'Vincom Thảo Điền, Quận 2, TP HCM'),
(5, 'Local Brand X', 'Lê Brand', 'hello@brandx.vn', '0933444555', 'Quận Đống Đa, Hà Nội');

INSERT INTO coupons (id, code, description, discount_type, discount_value, min_order_value, max_discount_value, start_date, end_date, usage_limit, used_count, is_active) VALUES
(1, 'SALE10', 'Giảm 10%', 'PERCENTAGE', 10, 500000, 500000, '2025-01-01 00:00:00', '2030-12-31 23:59:59', 100, 0, true),
(2, 'MINUS50K', 'Giảm 50K', 'FIXED_AMOUNT', 50000, 200000, NULL, '2025-01-01 00:00:00', '2030-12-31 23:59:59', 200, 0, true);

-- Thực thể chính
-- Password là "123456" đã được băm bằng Bcrypt
INSERT INTO users (id, username, email, password, full_name, created_at, updated_at) VALUES
(1, 'admin', 'admin@gmail.com', '$2a$10$C7wL4M6K4GZ9G3.r.Q4M4eH6j0n7o9QW/K2D/8J2r2z9L4m4eH6j0n7', 'Quản Trị Viên', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'nguyenvana', 'vana@gmail.com', '$2a$10$C7wL4M6K4GZ9G3.r.Q4M4eH6j0n7o9QW/K2D/8J2r2z9L4m4eH6j0n7', 'Nguyễn Văn A', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 'tranthib', 'thib@gmail.com', '$2a$10$C7wL4M6K4GZ9G3.r.Q4M4eH6j0n7o9QW/K2D/8J2r2z9L4m4eH6j0n7', 'Trần Thị B', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO users_roles (user_id, role_id) VALUES
(1, 1),
(2, 2),
(3, 2);

INSERT INTO addresses (id, full_name, phone_number, province, district, ward, detail_address, is_default, user_id) VALUES
(1, 'Nguyễn Văn A', '0987654321', 'Hà Nội', 'Cầu Giấy', 'Dịch Vọng', 'Số 10 Ngõ 2', true, 2),
(2, 'Trần Thị B', '0912345678', 'Hồ Chí Minh', 'Quận 1', 'Bến Nghé', 'Tòa nhà Bitexco', true, 3);

INSERT INTO products (id, name, slug, summary, content, brand, price, featured_image, category_id, supplier_id) VALUES
(1, 'iPhone 15 Pro Max', 'iphone-15-pro-max', 'Đỉnh cao công nghệ', 'Nội dung chi tiết iPhone 15 Pro Max', 'Apple', 30000000, 'iphone15promax.jpg', 4, 1),
(2, 'iPhone 14 Pro', 'iphone-14-pro', 'Mạnh mẽ và nhỏ gọn', 'Nội dung chi tiết iPhone 14 Pro', 'Apple', 25000000, 'iphone14pro.jpg', 4, 1),
(3, 'Samsung Galaxy S24 Ultra', 'samsung-galaxy-s24-ultra', 'Vua Android', 'Nội dung chi tiết Galaxy S24 Ultra', 'Samsung', 32000000, 's24ultra.jpg', 5, 2),
(4, 'Samsung Galaxy Z Fold 5', 'samsung-galaxy-z-fold-5', 'Màn hình gập đỉnh cao', 'Nội dung chi tiết Z Fold 5', 'Samsung', 40000000, 'zfold5.jpg', 5, 2),
(5, 'MacBook Air M2', 'macbook-air-m2', 'Mỏng nhẹ, mạnh mẽ', 'Nội dung chi tiết Macbook Air M2', 'Apple', 27000000, 'macbookairm2.jpg', 2, 1),
(6, 'MacBook Pro 14 M3', 'macbook-pro-14-m3', 'Dành cho chuyên gia', 'Nội dung chi tiết Macbook Pro M3', 'Apple', 40000000, 'macbookpro14.jpg', 2, 1),
(7, 'Galaxy Book 3 Pro', 'galaxy-book-3-pro', 'Làm việc chuyên nghiệp', 'Nội dung chi tiết Galaxy Book', 'Samsung', 30000000, 'galaxybook.jpg', 2, 2),
(8, 'AirPods Pro 2', 'airpods-pro-2', 'Chống ồn xuất sắc', 'Nội dung chi tiết AirPods', 'Apple', 6000000, 'airpods.jpg', 3, 1),
(9, 'Galaxy Buds 2 Pro', 'galaxy-buds-2-pro', 'Âm thanh đỉnh cao', 'Nội dung chi tiết Galaxy Buds', 'Samsung', 4000000, 'galaxybuds.jpg', 3, 2),
(10, 'Ốp lưng iPhone 15', 'op-lung-iphone-15', 'Bảo vệ toàn diện', 'Nội dung chi tiết Ốp lưng', 'Apple', 1000000, 'oplung.jpg', 3, 1),
(11, 'Áo thun Polo cơ bản', 'ao-thun-polo-co-ban', 'Vải cá sấu thoáng mát', 'Chất liệu 100% cotton cá sấu, co giãn 4 chiều, thấm hút mồ hôi cực tốt. Form ôm vừa vặn tôn dáng.', 'Zara', 350000, 'polo_basic.jpg', 8, 3),
(12, 'Áo thun Oversize Streetwear', 'ao-thun-oversize', 'Form rộng rãi thoải mái', 'Áo phông dáng oversize năng động, họa tiết in nổi sắc nét, vải cotton tici chống xù lông.', 'Local Brand X', 250000, 'oversize_tee.jpg', 8, 5),
(13, 'Quần Jeans Slimfit', 'quan-jeans-slimfit', 'Co giãn, tôn dáng', 'Quần chất denim cao cấp, độ co giãn nhẹ, giữ form tốt không bị nhão sau nhiều lần giặt.', 'H&M', 550000, 'jeans_slim.jpg', 9, 4),
(14, 'Quần Jeans Rách Gối Bụi Bặm', 'quan-jeans-rach', 'Phong cách đường phố', 'Thiết kế rách gối cá tính, wash màu xám đen bụi bặm, cạp quần chắc chắn.', 'Zara', 650000, 'jeans_rach.jpg', 9, 3),
(15, 'Váy hoa nhí mùa hè', 'vay-hoa-nhi', 'Dịu dàng, nữ tính', 'Chất lụa voan mềm mại, có lớp lót trong không sợ lộ. Họa tiết hoa nhí vintage phù hợp đi biển, dạo phố.', 'H&M', 450000, 'vay_hoa.jpg', 10, 4),
(16, 'Váy body dự tiệc trễ vai', 'vay-body-tre-vai', 'Sang trọng, quyến rũ', 'Thiết kế bodycon ôm sát đường cong, chất nhung tăm sang trọng, điểm nhấn trễ vai quyến rũ.', 'Zara', 850000, 'vay_body.jpg', 10, 3),
(17, 'Áo sơ mi lụa công sở', 'ao-so-mi-lua', 'Thanh lịch, mềm mại', 'Sơ mi cổ đức truyền thống, chất lụa ngọc trai bóng nhẹ, chống nhăn tuyệt đối.', 'H&M', 400000, 'somi_lua.jpg', 7, 4),
(18, 'Quần âu dáng suông nam', 'quan-au-suong', 'Lịch lãm, trẻ trung', 'Quần tây cạp cao dáng suông ống rộng style Hàn Quốc, vải kaki pha thun không nhăn.', 'Zara', 500000, 'quan_au.jpg', 6, 3),
(19, 'Áo khoác Bomber', 'ao-khoac-bomber', 'Giữ ấm, năng động', 'Bomber chần bông 2 lớp siêu ấm, phối bo chun tay áo và gấu áo chuẩn form.', 'Local Brand X', 750000, 'bomber.jpg', 6, 5),
(20, 'Set đồ thể thao nữ', 'set-the-thao-nu', 'Thoải mái vận động', 'Bộ đồ thun gân tăm gồm áo croptop và quần short mút mồ hôi, co giãn cực tốt.', 'Local Brand X', 300000, 'set_thethao.jpg', 7, 5);

-- Thực thể phụ thuộc
INSERT INTO product_variants (id, name, sku, price, stock_quantity, product_id) VALUES
(1, '256GB - Titan Tự nhiên', 'IP15PM-256-NAT', 30000000, 50, 1),
(2, '512GB - Titan Tự nhiên', 'IP15PM-512-NAT', 35000000, 30, 1),
(3, '256GB - Đen', 'IP15PM-256-BLK', 30000000, 40, 1),
(4, '128GB - Tím', 'IP14P-128-PUR', 25000000, 20, 2),
(5, '256GB - Vàng', 'IP14P-256-GLD', 28000000, 15, 2),
(6, '256GB - Đen Titan', 'S24U-256-BLK', 32000000, 45, 3),
(7, '512GB - Xám Titan', 'S24U-512-GRY', 37000000, 25, 3),
(8, '256GB - Xanh Icy', 'ZF5-256-BLU', 40000000, 10, 4),
(9, '512GB - Đen Phantom', 'ZF5-512-BLK', 45000000, 5, 4),
(10, '8GB/256GB - Midnight', 'MBA-M2-8-256-MID', 27000000, 30, 5),
(11, '16GB/512GB - Starlight', 'MBA-M2-16-512-STA', 35000000, 20, 5),
(12, '18GB/512GB - Space Black', 'MBP-M3-18-512-BLK', 40000000, 15, 6),
(13, '36GB/1TB - Silver', 'MBP-M3-36-1TB-SIL', 60000000, 5, 6),
(14, '16GB/512GB - Graphite', 'GB3P-16-512-GRA', 30000000, 20, 7),
(15, 'Trắng', 'APP2-WHI', 6000000, 100, 8),
(16, 'Tím', 'GB2P-PUR', 4000000, 80, 9),
(17, 'Đen', 'GB2P-BLK', 4000000, 70, 9),
(18, 'Trong suốt', 'OP-IP15-CLR', 1000000, 200, 10),
(19, 'Đen - S', 'POLO-BLK-S', 350000, 50, 11),
(20, 'Đen - M', 'POLO-BLK-M', 350000, 60, 11),
(21, 'Trắng - M', 'POLO-WHI-M', 350000, 45, 11),
(22, 'Trắng - L', 'POLO-WHI-L', 350000, 40, 11),
(23, 'Xám - Freesize', 'OVZ-GRY-FS', 250000, 100, 12),
(24, 'Trắng - Freesize', 'OVZ-WHI-FS', 250000, 80, 12),
(25, 'Xanh nhạt - 30', 'JSL-LBL-30', 550000, 30, 13),
(26, 'Xanh nhạt - 31', 'JSL-LBL-31', 550000, 30, 13),
(27, 'Xanh đậm - 30', 'JSL-DBL-30', 550000, 40, 13),
(28, 'Xanh đậm - 31', 'JSL-DBL-31', 550000, 35, 13),
(29, 'Đen - 29', 'JRA-BLK-29', 650000, 20, 14),
(30, 'Đen - 30', 'JRA-BLK-30', 650000, 25, 14),
(31, 'Đen - 31', 'JRA-BLK-31', 650000, 15, 14),
(32, 'Vàng nhạt - S', 'VHN-YEL-S', 450000, 50, 15),
(33, 'Vàng nhạt - M', 'VHN-YEL-M', 450000, 45, 15),
(34, 'Hồng - S', 'VHN-PNK-S', 450000, 30, 15),
(35, 'Đỏ - S', 'VBD-RED-S', 850000, 20, 16),
(36, 'Đỏ - M', 'VBD-RED-M', 850000, 15, 16),
(37, 'Đen - S', 'VBD-BLK-S', 850000, 25, 16),
(38, 'Đen - M', 'VBD-BLK-M', 850000, 25, 16),
(39, 'Trắng - S', 'SML-WHI-S', 400000, 60, 17),
(40, 'Trắng - M', 'SML-WHI-M', 400000, 55, 17),
(41, 'Be - M', 'SML-BEI-M', 400000, 40, 17),
(42, 'Xám - 30', 'QAU-GRY-30', 500000, 30, 18),
(43, 'Xám - 31', 'QAU-GRY-31', 500000, 35, 18),
(44, 'Đen - 31', 'QAU-BLK-31', 500000, 45, 18),
(45, 'Rêu - M', 'BMB-GRN-M', 750000, 15, 19),
(46, 'Rêu - L', 'BMB-GRN-L', 750000, 20, 19),
(47, 'Đen - L', 'BMB-BLK-L', 750000, 25, 19),
(48, 'Hồng - Freesize', 'STT-PNK-FS', 300000, 60, 20),
(49, 'Xanh bơ - Freesize', 'STT-GRN-FS', 300000, 55, 20);

INSERT INTO product_images (id, image_url, is_main, sort_order, product_id) VALUES
(1, 'ip15-1.jpg', true, 1, 1), (2, 'ip15-2.jpg', false, 2, 1),
(3, 'ip14-1.jpg', true, 1, 2), (4, 'ip14-2.jpg', false, 2, 2),
(5, 's24-1.jpg', true, 1, 3), (6, 's24-2.jpg', false, 2, 3),
(7, 'zf5-1.jpg', true, 1, 4), (8, 'zf5-2.jpg', false, 2, 4),
(9, 'mba-1.jpg', true, 1, 5), (10, 'mba-2.jpg', false, 2, 5),
(11, 'mbp-1.jpg', true, 1, 6), (12, 'mbp-2.jpg', false, 2, 6),
(13, 'gb3-1.jpg', true, 1, 7), (14, 'gb3-2.jpg', false, 2, 7),
(15, 'ap2-1.jpg', true, 1, 8), (16, 'ap2-2.jpg', false, 2, 8),
(17, 'gb2-1.jpg', true, 1, 9), (18, 'gb2-2.jpg', false, 2, 9),
(19, 'op-1.jpg', true, 1, 10), (20, 'op-2.jpg', false, 2, 10),
(21, 'polo_basic_main.jpg', true, 1, 11), (22, 'polo_basic_sub.jpg', false, 2, 11),
(23, 'oversize_main.jpg', true, 1, 12), (24, 'oversize_sub.jpg', false, 2, 12),
(25, 'jeans_slim_main.jpg', true, 1, 13), (26, 'jeans_slim_sub.jpg', false, 2, 13),
(27, 'jeans_rach_main.jpg', true, 1, 14), (28, 'jeans_rach_sub.jpg', false, 2, 14),
(29, 'vay_hoa_main.jpg', true, 1, 15), (30, 'vay_hoa_sub.jpg', false, 2, 15),
(31, 'vay_body_main.jpg', true, 1, 16), (32, 'vay_body_sub.jpg', false, 2, 16),
(33, 'somi_lua_main.jpg', true, 1, 17), (34, 'somi_lua_sub.jpg', false, 2, 17),
(35, 'quan_au_main.jpg', true, 1, 18), (36, 'quan_au_sub.jpg', false, 2, 18),
(37, 'bomber_main.jpg', true, 1, 19), (38, 'bomber_sub.jpg', false, 2, 19),
(39, 'set_thethao_main.jpg', true, 1, 20), (40, 'set_thethao_sub.jpg', false, 2, 20);

INSERT INTO wishlists (id, user_id, product_id, created_at) VALUES
(1, 2, 1, CURRENT_TIMESTAMP),
(2, 3, 3, CURRENT_TIMESTAMP);

-- Giao dịch (Transaction)
INSERT INTO carts (id, user_id, created_at, updated_at) VALUES
(1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO cart_items (id, quantity, cart_id, variant_id) VALUES
(1, 1, 2, 1),
(2, 2, 3, 6);

INSERT INTO orders (id, user_id, total_price, status, shipping_full_name, shipping_phone, province, district, ward, detail_address, shipping_note, coupon_id, discount_amount, payment_method, created_at) VALUES
(1, 2, 30000000, 'DELIVERED', 'Nguyễn Văn A', '0987654321', 'Hà Nội', 'Cầu Giấy', 'Dịch Vọng', 'Số 10 Ngõ 2', 'Giao giờ hành chính', NULL, 0, 'COD', CURRENT_TIMESTAMP),
(2, 3, 32000000, 'PENDING', 'Trần Thị B', '0912345678', 'Hồ Chí Minh', 'Quận 1', 'Bến Nghé', 'Tòa nhà Bitexco', NULL, NULL, 0, 'VNPAY', CURRENT_TIMESTAMP);

INSERT INTO order_items (id, order_id, variant_id, quantity, price) VALUES
(1, 1, 1, 1, 30000000),
(2, 2, 6, 1, 32000000);

INSERT INTO payments (id, order_id, transaction_id, vnp_transaction_no, amount, bank_code, payment_status, vnp_response_code, payment_info, created_at) VALUES
(1, 1, 'TXN001', 'VNP001', 30000000, 'NCB', 'SUCCESS', '00', 'Thanh toan don hang 1', CURRENT_TIMESTAMP),
(2, 2, 'TXN002', NULL, 32000000, NULL, 'PENDING', NULL, 'Thanh toan don hang 2', CURRENT_TIMESTAMP);

INSERT INTO stock_movements (id, variant_id, quantity, movement_type, note, created_at) VALUES
(1, 1, 50, 'IN', 'Nhập kho lô đầu', CURRENT_TIMESTAMP),
(2, 6, 45, 'IN', 'Nhập kho đợt 1', CURRENT_TIMESTAMP),
(3, 1, -1, 'OUT', 'Bán đơn hàng 1', CURRENT_TIMESTAMP),
(4, 19, 50, 'IN', 'Nhập kho lô Áo Polo Đen S', CURRENT_TIMESTAMP),
(5, 20, 60, 'IN', 'Nhập kho lô Áo Polo Đen M', CURRENT_TIMESTAMP),
(6, 21, 45, 'IN', 'Nhập kho lô Áo Polo Trắng M', CURRENT_TIMESTAMP),
(7, 22, 40, 'IN', 'Nhập kho lô Áo Polo Trắng L', CURRENT_TIMESTAMP),
(8, 23, 100, 'IN', 'Nhập kho Áo Oversize Xám', CURRENT_TIMESTAMP),
(9, 24, 80, 'IN', 'Nhập kho Áo Oversize Trắng', CURRENT_TIMESTAMP),
(10, 25, 30, 'IN', 'Nhập kho Jeans Slimfit Xanh nhạt 30', CURRENT_TIMESTAMP),
(11, 26, 30, 'IN', 'Nhập kho Jeans Slimfit Xanh nhạt 31', CURRENT_TIMESTAMP),
(12, 27, 40, 'IN', 'Nhập kho Jeans Slimfit Xanh đậm 30', CURRENT_TIMESTAMP),
(13, 28, 35, 'IN', 'Nhập kho Jeans Slimfit Xanh đậm 31', CURRENT_TIMESTAMP),
(14, 29, 20, 'IN', 'Nhập kho Jeans rách Đen 29', CURRENT_TIMESTAMP),
(15, 30, 25, 'IN', 'Nhập kho Jeans rách Đen 30', CURRENT_TIMESTAMP),
(16, 31, 15, 'IN', 'Nhập kho Jeans rách Đen 31', CURRENT_TIMESTAMP),
(17, 32, 50, 'IN', 'Nhập kho Váy hoa nhí Vàng S', CURRENT_TIMESTAMP),
(18, 33, 45, 'IN', 'Nhập kho Váy hoa nhí Vàng M', CURRENT_TIMESTAMP),
(19, 34, 30, 'IN', 'Nhập kho Váy hoa nhí Hồng S', CURRENT_TIMESTAMP),
(20, 35, 20, 'IN', 'Nhập kho Váy body Đỏ S', CURRENT_TIMESTAMP),
(21, 36, 15, 'IN', 'Nhập kho Váy body Đỏ M', CURRENT_TIMESTAMP),
(22, 37, 25, 'IN', 'Nhập kho Váy body Đen S', CURRENT_TIMESTAMP),
(23, 38, 25, 'IN', 'Nhập kho Váy body Đen M', CURRENT_TIMESTAMP),
(24, 39, 60, 'IN', 'Nhập kho Sơ mi lụa Trắng S', CURRENT_TIMESTAMP),
(25, 40, 55, 'IN', 'Nhập kho Sơ mi lụa Trắng M', CURRENT_TIMESTAMP),
(26, 41, 40, 'IN', 'Nhập kho Sơ mi lụa Be M', CURRENT_TIMESTAMP),
(27, 42, 30, 'IN', 'Nhập kho Quần âu Xám 30', CURRENT_TIMESTAMP),
(28, 43, 35, 'IN', 'Nhập kho Quần âu Xám 31', CURRENT_TIMESTAMP),
(29, 44, 45, 'IN', 'Nhập kho Quần âu Đen 31', CURRENT_TIMESTAMP),
(30, 45, 15, 'IN', 'Nhập kho Bomber Rêu M', CURRENT_TIMESTAMP),
(31, 46, 20, 'IN', 'Nhập kho Bomber Rêu L', CURRENT_TIMESTAMP),
(32, 47, 25, 'IN', 'Nhập kho Bomber Đen L', CURRENT_TIMESTAMP),
(33, 48, 60, 'IN', 'Nhập kho Set thể thao Hồng', CURRENT_TIMESTAMP),
(34, 49, 55, 'IN', 'Nhập kho Set thể thao Xanh bơ', CURRENT_TIMESTAMP);

INSERT INTO reviews (id, rating, comment, status, product_id, user_id, user_full_name, created_at) VALUES
(1, 5, 'Sản phẩm tuyệt vời!', true, 1, 2, 'Nguyễn Văn A', CURRENT_TIMESTAMP);

-- Cập nhật sequence (Dành cho PostgreSQL, tránh lỗi duplicate key sau khi insert cứng ID)
ALTER SEQUENCE roles_id_seq RESTART WITH 200;
ALTER SEQUENCE categories_id_seq RESTART WITH 200;
ALTER SEQUENCE suppliers_id_seq RESTART WITH 200;
ALTER SEQUENCE users_id_seq RESTART WITH 200;
ALTER SEQUENCE addresses_id_seq RESTART WITH 200;
ALTER SEQUENCE products_id_seq RESTART WITH 200;
ALTER SEQUENCE product_variants_id_seq RESTART WITH 200;
ALTER SEQUENCE product_images_id_seq RESTART WITH 200;
ALTER SEQUENCE coupons_id_seq RESTART WITH 200;
ALTER SEQUENCE wishlists_id_seq RESTART WITH 200;
ALTER SEQUENCE carts_id_seq RESTART WITH 200;
ALTER SEQUENCE cart_items_id_seq RESTART WITH 200;
ALTER SEQUENCE orders_id_seq RESTART WITH 200;
ALTER SEQUENCE order_items_id_seq RESTART WITH 200;
ALTER SEQUENCE payments_id_seq RESTART WITH 200;
ALTER SEQUENCE stock_movements_id_seq RESTART WITH 200;
ALTER SEQUENCE reviews_id_seq RESTART WITH 200;
ALTER SEQUENCE refresh_tokens_id_seq RESTART WITH 200;