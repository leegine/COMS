head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderAcceptResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ����������t���X�|���X�N���X(WEB3EquityOrderAcceptResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/22 �^�� (���u) �V�K�쐬
*/

package webbroker3.equity.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * �i����������t���X�|���X�j�B<br>
 * <br>
 * ����������t���X�|���X�N���X
 * @@version 1.0
 */
public class WEB3EquityOrderAcceptResponse extends WEB3BackResponse 
{
   
   /**
    * �|�������t�B�b�N�^�C�v�B<BR>
    */
   public static  final String PTYPE = "equity_order_accept";
   
   /**
    * �V���A���o�[�W����UID�B<BR>
    */
   public static  final long serialVersionUID = 200402241330L;
   
   /**
    * �R���X�g���N�^�B<BR>
    * @@param request ���N�G�X�g�N���X
    * @@param l_request
    * @@roseuid 4034961F0150
    */
   public WEB3EquityOrderAcceptResponse(WEB3BackRequest l_request) 
   {
       super(l_request);
   }
   
   /**
    * �R���X�g���N�^�B<BR>
    * @@roseuid 4034961F0141
    */
   public WEB3EquityOrderAcceptResponse() 
   {
      
   }
}
@
