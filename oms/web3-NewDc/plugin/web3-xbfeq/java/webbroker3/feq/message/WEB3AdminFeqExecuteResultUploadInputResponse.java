head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.33.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqExecuteResultUploadInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO��������茋�ʃA�b�v���[�h���̓��X�|���X(WEB3AdminFeqExecuteResultUploadInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/15 �A�C��(���u) �V�K�쐬
                 : 2005/08/02 �s�p(���u) ���r���[
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��ҊO��������茋�ʃA�b�v���[�h���̓��X�|���X)<BR>
 * �Ǘ��ҊO��������茋�ʃA�b�v���[�h���̓��X�|���X�N���X
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminFeqExecuteResultUploadInputResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_executeResultUploadInput";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200507121517L;  
    
    /**
     * @@roseuid 42CE39F9030D
     */
    public WEB3AdminFeqExecuteResultUploadInputResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminFeqExecuteResultUploadInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }  
}
@