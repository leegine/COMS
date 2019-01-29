head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.03.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualBuyInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M�����t�������̓��N�G�X�g�N���X(WEB3MutualBuyInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/04 ���E (���u) �V�K�쐬
                   2004/08/23 ������ (���u) ���r���[ 
*/
package webbroker3.mf.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �����M�����t�������̓��N�G�X�g�N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3MutualBuyInputRequest extends WEB3GenRequest
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_buy_input";
    
    /**
     * (ID)<BR>
     * ���M����ID
     */
    public String id;
    
    /**
     * (�d�q���`�F�b�N�t���O)<BR>
     * �d�q���`�F�b�N�t���O<BR>
     * <BR>
     * true�F�`�F�b�N�v <BR>
     * false�F�`�F�b�N�s�v
     */
    public boolean batoCheckFlag;
    
    /**
     * (��ʃR�[�h)<BR>
     * ��ʃR�[�h
     */
    public String typeCode;

    /**
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 40A87D1E023A
     */
    public WEB3MutualBuyInputRequest()
    {
    }
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408091539L;
    
    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * ���M���t�������̓��X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40A87D2E0111
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3MutualBuyInputResponse(this);
    }
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualBuyInputRequest.class);
        
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P)�@@ID�`�F�b�N<BR>
     * �@@this.ID��null�̏ꍇ�A��O���X���[����B<BR>
     *        class: WEB3BusinessLayerException<BR>
     *        tag:   BUSINESS_ERROR_00080 <BR>
     * 
     * @@roseuid 40A87D1900F2
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //this.ID��null�̏ꍇ�A��O���X���[����
        if (WEB3StringTypeUtility.isEmpty(this.id))
        {
            log.debug("�h�c�����w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�h�c�����w��ł��B");
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
