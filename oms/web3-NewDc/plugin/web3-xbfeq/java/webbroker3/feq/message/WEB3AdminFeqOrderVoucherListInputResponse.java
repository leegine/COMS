head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.25.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqOrderVoucherListInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO���������������`�[�ꗗ���̓��X�|���X(WEB3AdminFeqOrderVoucherListInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/18 �s�p (���u) �V�K�쐬
                 : 2005/08/02 ���U (���u) ���r���[
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��ҊO���������������`�[�ꗗ���̓��X�|���X)<BR>
 * �Ǘ��ҊO���������������`�[�ꗗ���̓��X�|���X�N���X
 *   
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3AdminFeqOrderVoucherListInputResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_feq_orderVoucherListInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121526L;
        
    /**
     * (�s��R�[�h�ꗗ)<BR>
     * �s��R�[�h�̔z��
     */
    public String[] marketCodeList;
    
    /**
     * @@roseuid 42CE39FB0177
     */
    public WEB3AdminFeqOrderVoucherListInputResponse() 
    {
     
    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminFeqOrderVoucherListInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }    

}
@
