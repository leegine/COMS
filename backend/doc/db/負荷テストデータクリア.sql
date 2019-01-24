
truncate table eqtype_order_action;
truncate table eqtype_order_unit;
truncate table eqtype_order;
truncate table oms_con_order_request;
truncate table rls_con_order_hit_notify;

alter table rls_market_rsp disable constraint rls_market_rsp_ord_fk;
alter table rls_oms_order_gen disable constraint rls_oms_order_gen_fk;
alter table rls_cond_order_hist disable constraint rls_cond_order_hist_fk;
alter table rls_chain_cond disable constraint rls_chain_cond_fk;
alter table rls_oms_order disable constraint rls_oms_order_cond_order_fk;
alter table rls_price_cond disable constraint rls_price_cond_fk;
alter table rls_time_cond disable constraint rls_time_cond_fk;

truncate table rls_cond_order_hist reuse storage;
truncate table rls_cond_order reuse storage;
truncate table rls_oms_order_gen reuse storage;
truncate table rls_oms_order reuse storage;
truncate table rls_price_cond reuse storage;
truncate table rls_time_cond reuse storage;
truncate table rls_chain_cond reuse storage;
truncate table rls_market_rsp reuse storage;

alter table rls_time_cond enable constraint rls_time_cond_fk;
alter table rls_price_cond enable constraint rls_price_cond_fk;
alter table rls_oms_order enable constraint rls_oms_order_cond_order_fk;
alter table rls_chain_cond enable constraint rls_chain_cond_fk;
alter table rls_market_rsp enable constraint rls_market_rsp_ord_fk;
alter table rls_oms_order_gen enable constraint rls_oms_order_gen_fk;
alter table rls_cond_order_hist enable constraint rls_cond_order_hist_fk;

commit;