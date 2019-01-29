head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.58.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondProductRegistInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҍ������o�^���̓��N�G�X�g(WEB3AdminBondProductRegistInputRequest.java)
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


/**
 * (�Ǘ��ҍ������o�^���̓��N�G�X�g)<BR>
 * �Ǘ��ҍ������o�^���N�G�X�g�N���X
 * 
 * @@author ��іQ
 * @@version 1.0
 */
public class WEB3AdminBondProductRegistInputRequest extends WEB3GenRequest
{
    /**
     *�@@���O���[�e�B���e�B<BR> 
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondProductRegistInputRequest.class);
    
    /**
     *�@@PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_product_regist_input";

    /**
     *�@@SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200608171100L;
    
    /**
     * (�����R�[�h(WEB3))<BR>
     * �����R�[�h(WEB3)
     */
    public String productCode;
    
    /**
     * @@roseuid 44E3363D00DA
     */
    public WEB3AdminBondProductRegistInputRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P)�@@ID�`�F�b�N <BR>
     * �@@this.�����R�[�h�iWEB3�j==null�̏ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00079
     * @@throws WEB3BaseException
     * @@roseuid 44BB31D601C9
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�����N�G�X�g�f�[�^�̐������`�F�b�N���s���B
        //�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j 
        //�P)�@@ID�`�F�b�N 
        //this.�����R�[�h�iWEB3�j==null�̏ꍇ�A��O���X���[����B
        if (this.productCode == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����R�[�h�����w��ł��B");
        }
        log.exiting(STR_METHOD_NAME);      
    }
    
    /**
     * (create���X�|���X)<BR>
     * (createResponse����)<BR>
     * <BR>
     * �����������o�^���̓��X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B
     * @@return WEB3GenResponse
     * @@roseuid 44BB30ED0126
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminBondProductRegistInputResponse(this);
    }
}
@
