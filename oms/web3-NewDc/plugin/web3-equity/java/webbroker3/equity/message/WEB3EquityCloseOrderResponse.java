head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityCloseOrderResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������ʒm���X�|���X�N���X(WEB3EquityCloseOrderResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/22 ���� (���u) �V�K�쐬
                   2004/12/06 �����a��(SRA) �c�Č��Ή� �m��.�R�R�T
                   2004/12/21 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity.message;

import webbroker3.common.message.WEB3BackResponse;

/**
 * �i���������ʒm���X�|���X�j�B<br>
 * <br>
 * ���������ʒm���X�|���X�N���X
 * @@version 1.0
 */
public class WEB3EquityCloseOrderResponse extends WEB3BackResponse 
{
   
    /**
     * <p>�iPTYPE�j</p>
     */
    public static  final String PTYPE = "equity_close_order";

    /**
     * <p>�iSerialVersionUID�j</p>
     */
    public static  final long serialVersionUID = 200405200156L;
   
    /**
     * <p>�i���������ʒm���X�|���X�j�B</p>
     * <p>�R���X�g���N�^�B</p>
     */
   public WEB3EquityCloseOrderResponse() 
   {
    
   }

   /**
    * <p>�i���������ʒm���X�|���X�j�B</p>
    * <p>�R���X�g���N�^�B</p>
    * @@param l_request ���������ʒm���N�G�X�g
    */  
   public WEB3EquityCloseOrderResponse(WEB3EquityCloseOrderRequest l_request)
   {
       super(l_request);
   }   
}
@