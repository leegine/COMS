head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.39.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOLotResultUploadCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO���I���ʱ���۰�ފ���ڽ��ݽ�N���X(WEB3AdminIPOLotResultUploadCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 ���C�g (���u) �V�K�쐬
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * �Ǘ���IPO���I���ʱ���۰�ފ���ڽ��ݽ�N���X
 * 
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3AdminIPOLotResultUploadCompleteResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_IPO_lotResultUploadComplete";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408181639L;
    
    /**
     * @@roseuid 4112DF8A0100
     */
    public WEB3AdminIPOLotResultUploadCompleteResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     * @@roseuid 40E11ABE031E
     */
    public WEB3AdminIPOLotResultUploadCompleteResponse(WEB3GenRequest l_request) 
    {
        
        super(l_request);
     
    }
}
@
