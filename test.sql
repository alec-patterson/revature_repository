--select * from customer_accounts ca;
--select * from customer_bank_information cbi ;
--select * from customer_personal_information cpi ;
--select * from login_information li ;

--delete from "BankingSystem".customer_personal_information where account_id = 11;
--delete from "BankingSystem".customer_bank_information where account_id = 11;
--delete from "BankingSystem".customer_accounts where account_id = 11;
--delete from "BankingSystem".login_information where id = 11;

--update "BankingSystem".customer_accounts set "needs approval" = false where account_id = 2;
--update "BankingSystem".customer_accounts set "role" = 'admin' where account_id = 1;

--select * from (select * from (select* from login_information li inner join customer_accounts ca on li.id = ca.login_id) as info1 inner join customer_personal_information cpi on customer_accounts.account_id = cpi.account_id) as info2 inner join customer_bank_information cbi on customer_personal_information.account_id = cbi.account_id;
select li.id, li.username, li.email, li.password, ca.account_id, ca.date_joined, ca."role", cpi."name", cpi.address, 
cpi.city, cpi.state, cpi.zip_code , cpi.phone_number, cbi.bank_id,
cbi.checking, cbi.saving
from "BankingSystem".login_information li 
full join "BankingSystem".customer_accounts ca on li.id = ca.login_id 
full join "BankingSystem".customer_personal_information cpi on ca.account_id = cpi.account_id 
full join "BankingSystem".customer_bank_information cbi on cpi.account_id = cbi.account_id;

--^^^ getting all information without repeating variables.

--alter table "BankingSystem".customer_accounts add role varchar;

--alter sequence "BankingSystem".account_id_seq restart with 1;
--alter sequence "BankingSystem".account_information_seq restart with 1;
--alter sequence "BankingSystem".accounts_account_id_seq restart with 1;
--alter sequence "BankingSystem".login_information_id_seq restart with 1;
--alter sequence "BankingSystem".user_login_seq restart with 1;
--alter sequence "BankingSystem".bank_id_seq restart with 1;

--select * from "BankingSystem".transactions t ;

select li.id, li.username, li.email, li.password, ca.account_id, ca.date_joined, ca."needs approval", ca."role", cpi."name", cpi.address, 
cpi.city, cpi.state, cpi.zip_code , cpi.phone_number, cbi.bank_id,
cbi.checking, cbi.saving
from "BankingSystem".login_information li 
full join "BankingSystem".customer_accounts ca on li.id = ca.login_id 
full join "BankingSystem".customer_personal_information cpi on ca.account_id = cpi.account_id 
full join "BankingSystem".customer_bank_information cbi on cpi.account_id = cbi.account_id 
where li.username = 'alexm'

delete from "BankingSystem".customer_personal_information where account_id = (select account_id from "BankingSystem".customer_accounts ca inner join "BankingSystem".login_information li on li.id = ca.account_id where li.username = 'dg');
delete from "BankingSystem".customer_accounts where login_id = (select id from "BankingSystem".login_information where username = 'dg');
delete from "BankingSystem".login_information where username = 'dg';

insert into "BankingSystem".customer_bank_information (account_id) values ((select account_id from "BankingSystem".customer_accounts ca inner join "BankingSystem".login_information li on li.id = ca.login_id where li.username = 'dg'));


update "BankingSystem".customer_accounts ca set "role" = 'employee' where ca.account_id = (select account_id from "BankingSystem".customer_accounts ca inner join "BankingSystem".login_information li on ca.login_id = li.id where li.username = 'bronzebucko');
delete from "BankingSystem".employee_information where login_id = (select id from "BankingSystem".login_information where username = 'royroy');
delete from "BankingSystem".employee_information where employee_id =8;

select * from "BankingSystem".employee_information ei ;

delete from "BankingSystem".employee_information where login_id = 2;
select password from "BankingSystem".login_information li where username = 'madcomrade98';

select * from "BankingSystem".login_information li where li.username = 'madcomrade98' or li.email = 'madcomrade98';

select * from "BankingSystem".login_information li where li.username = 'alec.patterson@revature.net' or li.email = 'alec.patterson@revature.net';
