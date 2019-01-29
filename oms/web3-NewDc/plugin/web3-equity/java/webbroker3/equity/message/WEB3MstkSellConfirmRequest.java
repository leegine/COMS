head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkSellConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����~�j�������t�����m�F���N�G�X�g�N���X(WEB3MstkSellConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 ���C�g (���u) �V�K�쐬
                   2004/12/13 �����a��(SRA) �c�Č��Ή� �m��.�S�O�V
*/

package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �i�����~�j�������t�����m�F���N�G�X�g�j�B<BR>
 * <BR>
 * �����~�j�������t�����m�F���N�G�X�g�N���X
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3MstkSellConfirmRequest extends WEB3GenRequest 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MstkSellConfirmRequest.class);
    
    /**
     * PTYPE
     */
    public static final String PTYPE = "mstk_sellConfirm";

    /**
     * SerialVersionUID
     */
    public static final long serialVersionUID = 200410101059L;    
    /**
     * (�����R�[�h)
     */
    public String productCode;
    
    /**
     * (��������)
     */
    public String orderQuantity;
    
    /**
     * @@roseuid 4167B05001BD
     */
    public WEB3MstkSellConfirmRequest() 
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
     * @@roseuid 4111BC730203
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

        log.debug("�����R�[�h�`�F�b�N");
        //�Q�j�@@�@@���������`�F�b�N
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
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4167B05001D1
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MstkSellConfirmResponse(this);
    }
}
@