CREATE TABLE IF NOT EXISTS "public"."department" (
                                       "id" uuid NOT NULL,
                                       "name" varchar(255) COLLATE "pg_catalog"."default",
                                       "created_by" uuid,
                                       "created_date" int8,
                                       "deleted" bool DEFAULT false,
                                       "modified_date" int8,
                                       CONSTRAINT "department_pkey" PRIMARY KEY ("id")
)
;

ALTER TABLE "public"."department"
    OWNER TO "postgres";

COMMENT ON COLUMN "public"."department"."name" IS 'Department name';