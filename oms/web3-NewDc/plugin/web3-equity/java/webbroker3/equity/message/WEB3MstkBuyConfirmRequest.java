head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkBuyConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����~�j�������t�����m�F���N�G�X�g(WEB3MstkBuyConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 ���]�� (���u) �V�K�쐬
                   2004/12/13 �����a��(SRA) �c�Č��Ή� �m��.�S�O�V
                   2004/12/
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
 * @@author ���]��
 * @@version 1.0
 */
public class WEB3MstkBuyConfirmRequest extends WEB3GenRequest 
{
    /**         
     * �i���O�o�̓��[�e�B���e�B�j�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MstkBuyConfirmRequest.class);
    /**
     * �iPTYPE�j�B
     */
    public static final String PTYPE = "mstk_buyConfirm";

    /**
     * �iSerialVersionUID�j�B
     */
    public static final long serialVersionUID = 200410101059L;

    /**
     * �i�����R�[�h�j�B
     */
    public String productCode;

    /**
     * �i���������j�B
     */
    public String orderQuantity;
    
    /**
     * �i�����~�j�������t�����m�F���N�G�X�g�j�B
     * <BR>
     * �R���X�g���N�^
     */
    public WEB3MstkBuyConfirmRequest() 
    {
     
    }
    
    /**
     * �ivalidate�j�B<BR>
     * <BR>
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�@@�����R�[�h�`�F�b�N<BR>
     * �@@�@@�@@�@@this.�����R�[�h���A�ȉ��̂����ꂩ�ɊY������ꍇ�́A<BR>
     * �@@�@@�@@�@@�ȉ��̗�O���X���[����B<BR>
     * <BR>
     * �@@�@@�@@�Ethis.�����R�[�h��null�@@�@@�@@�@@�@@(�u�����R�[�h��null�v�̗�O���X���[)<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00079<BR>
     * <BR>
     * �Q�j�@@�@@���������`�F�b�N<BR>
     * �@@�@@�@@�@@this.�����������ȉ��̂����ꂩ�ɊY������ꍇ�́A<BR>
     * �@@�@@�@@�@@�ȉ��̗�O���X���[����B<BR>
     * <BR>
     * �@@�@@�@@�Ethis.����������null�@@�@@�@@�@@�@@(�u����������null�v�̗�O���X���[)<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00126<BR>
     * <BR>
     * �@@�@@�@@�Ethis.��������������<BR>
     * (�u���������������ȊO�v�̗�O���X���[)<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00901<BR>
     * <BR>
     * �@@�@@�@@�Ethis.�����������O�@@�@@ �@@�@@�@@ (�u����������0�ȉ��v�̗�O���X���[)<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00902<BR>
     * <BR>
     * �@@�@@�@@�Ethis.����������8byte<BR>
     * (�u����������8byte�ȏ�v�̗�O���X���[)<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00903
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        log.debug("�����R�[�h�`�F�b�N");
        if(this.productCode == null)
        {
            log.error("�u�����R�[�h��null�v�̗�O���X���[");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00079, getClass().getName() + "validate");
        }

        log.debug("���������`�F�b�N");
        if(this.orderQuantity == null)
        {
            log.error("�u����������null�v�̗�O���X���[");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00126, getClass().getName() + "validate");
        }
        if(!WEB3StringTypeUtility.isNumber(this.orderQuantity))
        {
            log.error("�u���������������ȊO�v�̗�O���X���[");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00901, getClass().getName() + "validate");
        }
        if(Double.parseDouble(this.orderQuantity) <= 0)
        {
            log.error("�u����������0�ȉ��v�̗�O���X���[");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00902, getClass().getName() + "validate");
        }
        if(WEB3StringTypeUtility.getByteLength(this.orderQuantity) > 8 )
        {
            log.error("�u����������8byte�ȏ�v�̗�O���X���[");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00903, getClass().getName() + "validate");
        }

        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �icreate���X�|���X�j�B
     * @@return WEB3GenResponse �����~�j�������t�����m�F���X�|���X
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MstkBuyConfirmResponse(this);
    }
}
@
