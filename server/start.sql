
CREATE TABLE IF NOT EXISTS login (
                                            login TEXT NOT NULL PRIMARY KEY,
                                            id_login int NOT NULL PRIMARY KEY,
                                            key int NOT NULL PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS blog (
                                           id int NOT NULL,
                                           message TEXT NOT NULL

)