head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.33.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqExchangeRegistInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO�������ב֓o�^���̓��X�|���X(WEB3AdminFeqExchangeRegistInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/20 �Ջ`�g (���u) �V�K�쐬
                 : 2005/08/03 �A�C��(���u) ���r���[       
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��ҊO�������ב֓o�^���̓��X�|���X)<BR>
 * �Ǘ��ҊO�������ב֓o�^���̓��X�|���X�N���X
 *   
 * @@author �Ջ`�g
 * @@version 1.0
 */
public class WEB3AdminFeqExchangeRegistInputResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_exchangeRegistInput";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200410101855L;  
    
    /**
     * (�ב֏��ꗗ)<BR>
     * �O�������ב֏��̔z��
     */
    public WEB3FeqExchangeUnit[] exchangeList;
    
    /**
     * @@roseuid 42CE39FF031C
     */
    public WEB3AdminFeqExchangeRegistInputResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminFeqExchangeRegistInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }  
}
@
