declare

	 rnd      number;
         rndPrice number;
         
         
	--テーブルの項目定義
	 account_id_type sub_account.account_id%TYPE;
	 sub_account_id_type sub_account.sub_account_id%TYPE;
	 product_id_type eqtype_order_unit.product_id%TYPE; 
	 stop_order_price eqtype_order_unit.stop_order_price%TYPE;
	 quantity eqtype_order_unit.quantity%TYPE;
	 price eqtype_order_unit.price%TYPE;
	 order_cond_operator number;

	--システム時間
	 sysdate_type date:=sysdate;

	--sleep制御用
	 i1 number:=0;


	--CURSOR
	
	CURSOR c1 IS 
	  SELECT account_id,sub_account_id 
	  FROM 　(select * from sub_account order by dbms_random.value) 
	  WHERE  rownum<50;        
	  

	TYPE rec_type IS RECORD ( account_id sub_account.account_id%TYPE,sub_account_id sub_account.sub_account_id%TYPE);
	rec rec_type;


	CURSOR c_tp IS 
	  SELECT traded_product_id,last_closing_price,lot_size,product_id
	  FROM 　eqtype_traded_product 
	  WHERE LAST_CLOSING_PRICE>0 and MARKET_ID=2601 and  rownum<1000;        

	TYPE rec_tp_type IS RECORD ( traded_product_id eqtype_traded_product.traded_product_id%TYPE,last_closing_price eqtype_traded_product.last_closing_price%TYPE,lot_size eqtype_traded_product.lot_size%TYPE,product_id eqtype_traded_product.product_id%TYPE);
	rec_tp rec_tp_type;


	begin
	
	i1:=1000000;
	
		  
		  OPEN c_tp;
		  LOOP
		    FETCH c_tp INTO rec_tp;
		    EXIT WHEN c_tp%NOTFOUND;
		  
		  
		  
				  OPEN c1;
				  LOOP
				    FETCH c1 INTO rec;
				    EXIT WHEN c1%NOTFOUND;

						begin

							i1:=i1+1;

							rnd := i1;
							--rndPrice:= rec_tp.last_closing_price;
							rndPrice:= 1;
							
							account_id_type:=rec.account_id;
							sub_account_id_type:=rec.sub_account_id;
							order_cond_operator:=1;
							
							quantity:=rec_tp.lot_size;
							--product_id_type:=rec_tp.product_id;
							product_id_type:=2601268900000;
							
							--price:=rec_tp.last_closing_price;
							price:=1;
							
							stop_order_price:=rndprice;
							

							insert into eqtype_order values(rnd,account_id_type,sub_account_id_type,'1',sysdate_type,sysdate_type);
							insert into eqtype_order_unit values(rnd,account_id_type,sub_account_id_type,'26624','',rnd,'1','1','1','0','1','2601',quantity,'0','1','0','1',order_cond_operator,stop_order_price,'','2006/01/18','2006/01/13','','','','','1','1','1','1','0','','','','20060113',product_id_type,'1','1','2006/01/12','9015','06','','00100',price,rnd,'541100','0','0','11','1','10','','0','2','','','','','','0000','0','',sysdate_type,sysdate_type,'2','00','00','0',null,null);
							insert into eqtype_order_action values(rnd,account_id_type ,sub_account_id_type,rnd,rnd,'2601','1','1','0','1','0','1','1',stop_order_price,'','2006/01/13',quantity,'','','','1','1','1','','1',product_id_type,'1','541100','0','','0000','0',sysdate_type,sysdate_type,'','2');            
							insert into oms_con_order_request values
							(
							account_id_type, 
							sub_account_id_type, 
							4, --注文タイプ(逆指値)
							2, --リクエストタイプ(登録)
							rnd,
							1, --株式
							0, --子注文ID
							0, --シリアル番号
							0, --子注文銘柄タイプ
							0, --処理ステータス
							null,
							sysdate_type,
							sysdate_type
							);

							commit;

							--exception  
							--    when others then
							--    null;
						  end;


					--if mod(i1,100)=0 then
					--     dbms_lock.sleep(1);
					-- end if; 

				  END LOOP;
				  CLOSE c1;	
					  

			  END LOOP;
			  CLOSE c_tp;	
			

		    
		 
	end;


/