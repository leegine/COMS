head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkSellCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����~�j�������t�����������N�G�X�g�N���X(WEB3MstkSellCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 ���C�g (���u) �V�K�쐬
                   2004/12/13 �����a��(SRA) �c�Č��Ή� �m��.�S�O�V
*/

package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �i�����~�j�������t�����������N�G�X�g�j�B<BR>
 * <BR>
 * �����~�j�������t�����������N�G�X�g�N���X
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3MstkSellCompleteRequest extends WEB3GenRequest 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MstkSellCompleteRequest.class);
    
    /**
     * PTYPE
     */
    public static final String PTYPE = "mstk_sellComplete";

    /**
     * SerialVersionUID
     */
    public static final long serialVersionUID = 200410121732L;    
    /**
     * (�����R�[�h)
     */
    public String productCode;
    
    /**
     * (��������)
     */
    public String orderQuantity;
    
    /**
     * (�Ïؔԍ�)
     */
    public String password;
    
    /**
     * (�m�F���P��)
     */
    public String checkPrice;
    
    /**
     * (�m�F��������)<BR>
     * ���������s��
     */
    public Date checkDate;
    
    /**
     * (����ID)
     */
    public String orderId;
    
    /**
     * @@roseuid 4167B050000F
     */
    public WEB3MstkSellCompleteRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�@@�����R�[�h�`�F�b�N<BR>
     * �@@�@@�@@�@@this.�����R�[�h���A�ȉ��̂����ꂩ�ɊY������ꍇ�́A<BR>
     * �@@�@@�@@�@@�ȉ��̗�O���X���[����B<BR>
     * <BR>
     * �@@�@@�@@�Ethis.�����R�[�h��null(�u�����R�[�h��null�v�̗�O���X���[)<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00079<BR>
     * <BR>
     * �Q�j�@@�@@���������`�F�b�N<BR>
     * �@@�@@�@@�@@this.�����������ȉ��̂����ꂩ�ɊY������ꍇ�́A<BR>
     * �@@�@@�@@�@@�ȉ��̗�O���X���[����B<BR>
     * <BR>
     * �@@�@@�@@�Ethis.����������null(�u����������null�v�̗�O���X���[)<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00126<BR>
     * <BR>
     * �@@�@@�@@�Ethis.��������������(�u���������������ȊO�v�̗�O���X���[)<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00901<BR>
     * <BR>
     * �@@�@@�@@�Ethis.�����������O(�u����������0�ȉ��v�̗�O���X���[)<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00902<BR>
     * <BR>
     * �@@�@@�@@�Ethis.����������8byte(�u����������8byte�ȏ�v�̗�O���X���[)<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00903<BR>
     * <BR>
     * �R�j�@@�m�F���P���`�F�b�N<BR>
     * �@@�@@�@@this.�m�F���P����null�ł������ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00206<BR>
     * <BR>
     * �S�j�@@�m�F���������`�F�b�N<BR>
     * �@@�@@�@@this.�m�F��������null�ł������ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00078<BR>
     * <BR>
     * �T�j�@@����ID�`�F�b�N<BR>
     * �@@�@@�@@this.����ID��null�ł������ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00600<BR>
     * <BR>
     * @@roseuid 4111BE7C01A5
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�@@�����R�[�h�`�F�b�N
        log.debug("�����R�[�h�`�F�b�N");
        if(productCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079, 
                this.getClass().getName() + STR_METHOD_NAME);            
        }

        //�Q�j�@@�@@���������`�F�b�N
        log.debug("���������`�F�b�N");
        if(orderQuantity == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00126, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        if(!WEB3StringTypeUtility.isNumber(orderQuantity))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00901, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        double l_dblOrderQuantity = Double.parseDouble(orderQuantity);
        if(l_dblOrderQuantity <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00902, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        if(WEB3StringTypeUtility.getNubmerLength(orderQuantity) > 8)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00903, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //�R�j�@@�m�F���P���`�F�b�N
        log.debug("�m�F���P���`�F�b�N");
        if(checkPrice == null)
        {
            log.error("�G���[�F�m�F���P����null����I");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00206, 
                this.getClass().getName() + STR_METHOD_NAME);
            
        }
        
        //�S�j�@@�m�F���������`�F�b�N
        log.debug("�m�F���������`�F�b�N");
        if(checkDate == null)
        {
            log.error("�G���[�F�m�F����������null����I");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00078, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //�T�j�@@����ID�`�F�b�N
        log.debug("����ID�`�F�b�N");
        if(orderId == null)
        {
            log.error("�G���[�F����ID��null����I");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4167B050002D
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MstkSellCompleteResponse(this);
    }
}
@
