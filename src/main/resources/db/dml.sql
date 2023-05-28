INSERT INTO
    ecommerence.categories (id, "name")
VALUES
    (
        '9cf471f4-5540-4b03-87c4-e6fe73397744',
        'Sporting Good'
    ),
    (
        '7aed5aec-74fc-4e9c-84ab-008a143c4cc2',
        'Furniture'
    ),
    (
        '258c3159-9d3d-4dc7-be30-bf45383d57b8',
        'Outdoor'
    );

INSERT INTO
    ecommerence.products (
        id,
        "name",
        description,
        price,
        qty_on_hand,
        category_id
    )
VALUES
    (
        'ed673793-d084-4ee9-8243-406e07b6632e',
        'Basketball',
        'NBA Basketball just like the ones used in the NBA.',
        50,
        1,
        '9cf471f4-5540-4b03-87c4-e6fe73397744'
    );


INSERT INTO roles (id, name) VALUES (1, "USER");
