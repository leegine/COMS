head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.20.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOrderExecNotifyResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�o���ʒm���X�|���X�N���X(WEB3FuturesOrderExecNotifyResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/22 䈋� (���u) �V�K�쐬
*/
package webbroker3.ifo.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;


/**
 * (�����w���敨�o���ʒm���X�|���X)<BR>
 * �����w���敨�o���ʒm���X�|���X�N���X<BR>
 */
public class WEB3FuturesOrderExecNotifyResponse extends WEB3BackResponse 
{
    /**
      * PTYPE<BR>
      */
     public static final String PTYPE = "futures_orderExecNotify";

     /**
      * SerialVersionUID<BR>
      */
     public static final long serialVersionUID = 200407221304L;
    
    /**
       * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
       * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
       *<BR>
       * @@param l_request ���N�G�X�g�I�u�W�F�N�g
       */
      protected WEB3FuturesOrderExecNotifyResponse(WEB3BackRequest l_request)
      {
          super(l_request);
      }
}
@
