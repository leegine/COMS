head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.15.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsOrderNotifyResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V���������ʒm���X�|���X(WEB3OptionsOrderNotifyResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/02 ������ (���u) �V�K�쐬
*/

package webbroker3.ifo.message;

import webbroker3.common.message.WEB3BackResponse;

/**
 * (�����w���I�v�V���������ʒm���X�|���X)<BR>
 * �����w���I�v�V���������ʒm���X�|���X�N���X<BR>
 * @@author  : ������
 * @@version : 1.0
 */
public class WEB3OptionsOrderNotifyResponse extends WEB3BackResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "options_orderNotify";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200412081451L;
   /**
    * @@roseuid 41AAE7E80186
    */
   public WEB3OptionsOrderNotifyResponse() 
   {
    
   }
   
   /**
    * (�R���X�g���N�^�B)<BR>
    * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
    * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
    *<BR>
    * @@param l_request ���N�G�X�g�I�u�W�F�N�g
    */
   public WEB3OptionsOrderNotifyResponse(WEB3OptionsOrderNotifyRequest l_request)
   {
       super(l_request);
   }
}
@
