ALTER TABLE "patient" DROP CONSTRAINT patient_passport_k1;
ALTER TABLE "patient"  ADD UNIQUE (passport, company_id);