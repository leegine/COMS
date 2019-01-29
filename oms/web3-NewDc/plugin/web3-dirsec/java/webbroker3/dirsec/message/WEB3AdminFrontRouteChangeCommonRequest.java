head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.14.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFrontRouteChangeCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�����o�H�ؑ֋��ʃ��N�G�X�g (WEB3AdminOffFloorChangeCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/17  �Ӑ� (���u) �d�l�ύX���f��No.116
*/
package webbroker3.dirsec.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (�Ǘ��ҁE�����o�H�ؑ֋��ʃ��N�G�X�g)<BR>
 * <BR>
 * �Ǘ��ҁE�����o�H�ؑփT�[�r�X�i�m�F�^�����j���N�G�X�g�f�[�^�̃X�[�p�[�N���X�B
 * <BR>
 * <BR>
 * ------<English>---------------<BR>
 * <BR>
 * WEB3AdminFrontRouteChangeCommonRequest<BR>
 * <BR>
 * super class of WEB3AdminFrontRouteChangeService(validate/submit) request data<BR>
 * <BR>
 * @@author SCS.Sato
 * @@version 1.0
 */
public abstract class WEB3AdminFrontRouteChangeCommonRequest extends WEB3GenRequest {

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFrontRouteChangeCommonRequest.class);
        
    /**
     * �،���ЃR�[�h<BR>
     */
    public String institutionCode;
   
    /**
     * �ؑ֏��������敪<BR>
     */
    public String changeProcessDiv;
   
    /**
     * �ϊ��s��R�[�h<BR>
     */
    public String convertMarketCode;
   
    /**
     * �s��R�[�h<BR>
     */
    public String marketCode;
   
    /**
     * �����^�C�v<BR>
     */
    public String productType;
   
    /**
     * �����o�H�敪<BR>
     */
    public String submitOrderRouteDiv;
   
    /**
     * �ύX�㔭���o�H�敪<BR>
     */
    public String changedSubmitOrderRouteDiv;
   
    /**
     * �ؑ֋N���敪<BR>
     */
    public String changeStartDiv;
   
    /**
     * @@roseuid 42FFFED4039D
     */
    public WEB3AdminFrontRouteChangeCommonRequest() 
    {
    
    }

        
    /**
     * ���N���X�̃v���p�e�B�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * @@roseuid 41B7D3A40221
     * @@throws WEB3BusinessLayerException WEB3BusinessLayerException
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        // 1-1 if institutionCode is null, throw Exception.
        if (this.institutionCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00827,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        // 1-2 if changeProcessDiv is null, throw Exception.
        else if (this.changeProcessDiv == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02205,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        // 1-3 if convertMarketCode is null, throw Exception.
        else if (this.convertMarketCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02209,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        // 1-4 if marketCode is null, throw Exception.
        else if (this.marketCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00443,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        // 1-5 if productType is null, throw Exception.
        else if (this.productType == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01109,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        // 1-6 if submitOrderRouteDiv is null, throw Exception.
        else if (this.submitOrderRouteDiv == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02210,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        // 1-7 if changeStartDiv is null, throw Exception.
        else if (this.changeStartDiv == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02211,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
        
    /** (�� Javadoc)
     * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
     */
    public abstract WEB3GenResponse createResponse();
}
@
