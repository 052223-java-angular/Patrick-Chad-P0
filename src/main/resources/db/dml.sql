INSERT INTO
    yourstore.categories (id, "name")
VALUES
    (
        'bdae5526-4e75-4a54-bda7-186788317888',
        'Sporting Goods'
    );

INSERT INTO
    yourstore.products (
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
        'bdae5526-4e75-4a54-bda7-186788317888'
    );


INSERT INTO roles (id, name) VALUES (1, "USER");
