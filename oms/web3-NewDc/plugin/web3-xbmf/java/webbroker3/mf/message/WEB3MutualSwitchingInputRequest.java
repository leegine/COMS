head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.02.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualSwitchingInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M���抷���̓��N�G�X�g�N���X(WEB3MutualSwitchingInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 ������ (���u) �V�K�쐬
                   2004/08/25 ���� (���u) ���r���[
                   2005/10/18 ��O�� (���u) �t�B�f���e�B�Ή�
*/

package webbroker3.mf.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �����M���抷���̓��N�G�X�g�N���X
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3MutualSwitchingInputRequest extends WEB3GenRequest
{
    
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualSwitchingInputRequest.class);

    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_switching_input";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408091539L;

    /**
     * (���YID)<BR>
     * ���M���YID<BR>
     */
    public String id;
    
    /**
     * (�抷�����ID)<BR>
     * �抷�����ID<BR>
     */
    public String switchingProductId;
    
    /**
     * (�d�q���`�F�b�N�t���O)<BR>
     * �d�q���`�F�b�N�t���O <BR>
     * true�F�`�F�b�N�v <BR>
     * false�F�`�F�b�N�s�v <BR>
     */
    public boolean batoCheckFlag;

    /**
     * (��ʃR�[�h)<BR>
     * ��ʃR�[�h<BR>
     */
    public String typeCode;

    /**
     * (���M�抷���̓��N�G�X�g)<BR>
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 40A8A28A0218
     */
    public WEB3MutualSwitchingInputRequest()
    {

    }

    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * ���M�抷���̓��X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40A8A2B800A1
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3MutualSwitchingInputResponse(this);
    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P)�@@���YID�`�F�b�N 
     * �@@this.���YID==null�̏ꍇ�A��O���X���[����B 
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_01919 <BR>
     * <BR>
     * �Q)�@@�抷�����ID�`�F�b�N <BR>
     * �@@this.�抷�����ID==null�̏ꍇ�A��O���X���[����B <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_02268 <BR>
     * <BR>
     * @@roseuid 40A8A2AA0043
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        if (WEB3StringTypeUtility.isEmpty(this.id))
        {
            log.debug("���YID�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01919,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���YID�����w��ł��B");
        }
        
        if (WEB3StringTypeUtility.isEmpty(this.switchingProductId))
        {
            log.debug("�抷�����ID�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02268,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�抷�����ID�����w��ł��B");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
