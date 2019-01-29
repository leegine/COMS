head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.47.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondProductRegistCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҍ������o�^�������N�G�X�g(WEB3AdminBondProductRegistCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ��іQ (���u) �V�K�쐬
*/

package webbroker3.bd.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (�Ǘ��ҍ������o�^�������N�G�X�g)<BR>
 * �Ǘ��ҍ������o�^�������N�G�X�g�N���X
 * 
 * @@author ��іQ
 * @@version 1.0
 */
public class WEB3AdminBondProductRegistCompleteRequest extends WEB3AdminBondProductRegistCommonRequest
{
    /**
     *�@@���O���[�e�B���e�B<BR> 
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondProductRegistCompleteRequest.class);
    
    /**
     *�@@PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_product_regist_complete";

    /**
     *�@@SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200608171100L;
    
    /**
     * (�Ïؔԍ�)<BR>
     * �Ïؔԍ�
     */
    public String password;
    
    /**
     * @@roseuid 44E3363C0157
     */
    public WEB3AdminBondProductRegistCompleteRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j�X�[�p�[�N���X��validate()���\�b�h���R�[������B<BR>
     * <BR>
     * �Q)�@@�Ïؔԍ��`�F�b�N <BR>
     * �@@this.�Ïؔԍ�==null�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_01090
     * @@throws WEB3BaseException 
     * @@roseuid 44D69110033C
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�����N�G�X�g�f�[�^�̐������`�F�b�N���s���B 
        //�i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j 
        //�P�j�X�[�p�[�N���X��validate()���\�b�h���R�[������B
        super.validate();
        
        //�Q)�@@�Ïؔԍ��`�F�b�N
        //this.�Ïؔԍ�==null�̏ꍇ�A��O���X���[����B
        if (this.password == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�Ïؔԍ������w��ł��B");
        }
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (create���X�|���X)<BR>
     * (createResponse����)<BR>
     * <BR>
     * �������o�^�������N�G�X�g�𐶐����Ԃ��B
     * @@return WEB3GenResponse
     * @@roseuid 44B620020375
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminBondProductRegistCompleteResponse(this);
    }
}
@
