head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.34.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqForeignCostRegistInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO���������n�萔���o�^���̓��X�|���X(WEB3AdminFeqForeignCostRegistInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/20 �Ջ`�g (���u) �V�K�쐬
                 : 2005/08/03 �A�C��(���u) ���r���[       
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��ҊO���������n�萔���o�^���̓��X�|���X)<BR>
 * �Ǘ��ҊO���������n�萔���o�^���̓��X�|���X�N���X
 *   
 * @@author �Ջ`�g
 * @@version 1.0
 */
public class WEB3AdminFeqForeignCostRegistInputResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_foreignCostRegistInput";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200507121517L;  
    
    /**
     * (���n�萔�����ꗗ)<BR>
     * ���n�萔�����̔z��
     */
    public WEB3AdminFeqForeignCostUnit[] foreignCostRegist;
    
    /**
     * (�s��R�[�h�ꗗ)<BR>
     * �s��R�[�h�̔z��
     */
    public String[] marketList;
    
    /**
     * @@roseuid 42CE39FE038A
     */
    public WEB3AdminFeqForeignCostRegistInputResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminFeqForeignCostRegistInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }  
}
@
