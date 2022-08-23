DELETE FROM history_room WHERE room_id=(SELECT "id" FROM room  WHERE capacity=10);
DELETE FROM room_place WHERE room_id=(SELECT "id" FROM room  WHERE capacity=10);
DELETE FROM room  WHERE capacity=10;