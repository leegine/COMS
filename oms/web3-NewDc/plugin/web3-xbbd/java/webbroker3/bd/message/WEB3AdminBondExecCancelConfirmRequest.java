head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.53.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondExecCancelConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Җ�����m�F���N�G�X�g(WEB3AdminBondExecCancelConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ��іQ (���u) �V�K�쐬
*/

package webbroker3.bd.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ǘ��Җ�����m�F���N�G�X�g)<BR>
 * �Ǘ��Җ�����m�F���N�G�X�g
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */
public class WEB3AdminBondExecCancelConfirmRequest extends WEB3GenRequest
{
    /**
     *�@@���O���[�e�B���e�B<BR> 
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondExecCancelConfirmRequest.class);
    
    /**
     *�@@PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_exec_cancel_confirm";

    /**
     *�@@SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200608171100L;
    
    /**
     * (����ID)<BR>
     * ����ID
     */
    public String id;
    
    /**
     * @@roseuid 44E3363303D8
     */
    public WEB3AdminBondExecCancelConfirmRequest() 
    {
     
    }
       
    /**
     * �P)�@@����ID�`�F�b�N <BR>
     * �@@this.����ID==null�̏ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00600<BR>
     * <BR>
     * �@@this.����ID�����l�łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_01476
     * @@throws WEB3BaseException
     * @@roseuid 44B739EE01A5
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P)�@@����ID�`�F�b�N 
        //this.����ID==null�̏ꍇ�A��O���X���[����B
        if (this.id == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����ID�����w��ł��B");
        }
        
        //this.����ID�����l�łȂ��ꍇ�A��O���X���[����
        if (!WEB3StringTypeUtility.isNumber(this.id))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01476,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����ID�������ȊO�ł��B");
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (create���X�|���X)<BR>
     * (createResponse����)<BR>
     * <BR>
     * �Ǘ��Җ�����m�F���X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B
     * @@return WEB3GenResponse
     * @@roseuid 44BDE6920134
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminBondExecCancelConfirmResponse(this);
    }
}
@
