head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.39.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOLotResultUploadCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO���I���ʱ���۰�ފ���ظ��ăN���X(WEB3AdminIPOLotResultUploadCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 ���C�g (���u) �V�K�쐬
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * �Ǘ���IPO���I���ʱ���۰�ފ���ظ��ăN���X
 * 
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3AdminIPOLotResultUploadCompleteRequest extends WEB3GenRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_IPO_lotResultUploadComplete";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408131124L;
    
    /**
     * IPO�����h�c
     */
    public String id;
    
    /**
     * �A�b�v���[�h�h�c
     */
    public String uploadID;
    
    /**
     * �Ïؔԍ�
     */
    public String password;
    
    /**
     * @@roseuid 4112DAD40160
     */
    public WEB3AdminIPOLotResultUploadCompleteRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112DAD4017E
     */
    public WEB3GenResponse createResponse() 
    {

        return new WEB3AdminIPOLotResultUploadCompleteResponse(this);

    }
}
@
