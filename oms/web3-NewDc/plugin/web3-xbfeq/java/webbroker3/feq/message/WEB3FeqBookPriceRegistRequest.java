head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.27.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqBookPriceRegistRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������뉿�P���o�^���N�G�X�g(WEB3FeqBookPriceRegistRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ���� (���u) �V�K�쐬
                 : 2005/08/01 �s�p(���u) ���r���[   
Revesion History : 2008/01/16 �đo�g(���u) ���f��No.373
*/

package webbroker3.feq.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�O�������뉿�P���o�^���N�G�X�g)<BR>
 * �O�������뉿�P���o�^���N�G�X�g�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3FeqBookPriceRegistRequest extends WEB3GenRequest 
{    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "feq_bookPriceRegist";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121500L;
    
    /**
     * (�ۗL���YID)<BR>
     * �ۗL���YID<BR>
     */
    public String assetId;
    
    /**
     * (�ύX��T�Z�뉿�P��)<BR>
     * �ύX��T�Z�뉿�P��<BR>
     */
    public String aftBookPrice;

    /**
     * (�v�Z�p���͕뉿���z)<BR>
     * �v�Z�p���͕뉿���z<BR>
     */
    public String aftBookAmount;

    /**
     * (�c������)<BR>
     * �c������<BR>
     */
    public String balanceQuantity;

    /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqBookPriceRegistRequest.class);
    
    /**
     * @@roseuid 42CE3A05009C
     */
    public WEB3FeqBookPriceRegistRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�ۗL���YID�`�F�b�N<BR>
     * �@@�P�|�P�j�ۗL���YID��null�̏ꍇ�A<BR>
     * �@@�@@�u�ۗL���YID�������́v�̗�O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_01919<BR>
     * <BR>
     * �Q�j�ύX��T�Z�뉿�P���`�F�b�N<BR>
     * �@@�Q�|�P�j�ύX��T�Z�뉿�P�� == null ||�@@�v�Z�p���͕뉿���z == null �̏ꍇ�A<BR>
     * �@@�@@�u�K�{���ږ����́v�̗�O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02980<BR>
     * <BR>
     * �@@�Q�|�Q�j�ύX��T�Z�뉿�P�����ȉ��̏����ɊY������ꍇ�A<BR>
     * �@@�@@�u�ύX��T�Z�뉿�P�����s���Ȓl�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�E�ύX��T�Z�뉿�P�� != ���l<BR>
     * �@@�@@�@@�E�ύX��T�Z�뉿�P�� < 0<BR>
     * �@@�@@�@@�E�ύX��T�Z�뉿�P���̌��� > 8��<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_02110<BR>
     * <BR>
     * �@@�Q�|�R�j�v�Z�p���͕뉿���z�`�F�b�N<BR>
     * �@@�@@�ȉ��̏����ɊY������ꍇ�A�u�v�Z�p���͕뉿���z���s���Ȓl�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�E�v�Z�p���͕뉿���z != ���l<BR>
     * �@@�@@�@@�E�v�Z�p���͕뉿���z < 0<BR>
     * �@@�@@�@@�E�v�Z�p���͕뉿���z�̌��� >= 12��<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_02981<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 42A94A080377
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�ۗL���YID�`�F�b�N
        //�P�|�P�j�ۗL���YID��null�̏ꍇ�A
        //�u�ۗL���YID�������́v�̗�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.assetId))
        {
            log.debug("�ۗL���YID�����w��(null)�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01919,
                this.getClass().getName() + STR_METHOD_NAME,
                "�ۗL���YID�����w��(null)�ł��B" + this.assetId);
        }
  
        //�Q�j�ύX��T�Z�뉿�P���`�F�b�N
        //�Q�|�P�j�ύX��T�Z�뉿�P�� == null ||�@@�v�Z�p���͕뉿���z == null �̏ꍇ�A
        //�u�K�{���ږ����́v�̗�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.aftBookPrice)
            || WEB3StringTypeUtility.isEmpty(this.aftBookAmount))
        {
            log.debug("�K�{���ږ�����");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02980,
                this.getClass().getName() + STR_METHOD_NAME,
                "�K�{���ږ�����" + this.aftBookPrice);
        }

        //�Q�|�Q�j�ύX��T�Z�뉿�P�����ȉ��̏����ɊY������ꍇ�A
        //�u�ύX��T�Z�뉿�P�����s���Ȓl�v�̗�O���X���[����B
        //�E�ύX��T�Z�뉿�P�� != ���l
        //�E�ύX��T�Z�뉿�P�� < 0
        //�E�ύX��T�Z�뉿�P���̐������̌��� > 8��
        if (!WEB3StringTypeUtility.isNumber(this.aftBookPrice) ||
            Double.parseDouble(this.aftBookPrice) < 0 ||
            (this.aftBookPrice.split("\\."))[0].length() > 8)
         {
             log.debug("�ύX��T�Z�뉿�P�����s���Ȓl�ɂȂ��Ă��܂��B");
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BusinessLayerException(
                 WEB3ErrorCatalog.BUSINESS_ERROR_02110,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�ύX��T�Z�뉿�P�����s���Ȓl�ɂȂ��Ă��܂��B" + 
                 this.aftBookPrice);
         }

        //�@@�Q�|�R�j�v�Z�p���͕뉿���z�`�F�b�N
        //�@@�@@�ȉ��̏����ɊY������ꍇ�A�u�v�Z�p���͕뉿���z���s���Ȓl�v�̗�O���X���[����B
        //�@@�@@�@@�E�v�Z�p���͕뉿���z != ���l
        //�@@�@@�@@�E�v�Z�p���͕뉿���z < 0
        //�@@�@@�@@�E�v�Z�p���͕뉿���z�̌��� >= 12��
        if (!WEB3StringTypeUtility.isNumber(this.aftBookAmount)
            || Double.parseDouble(this.aftBookAmount) < 0
            || WEB3StringTypeUtility.getNubmerLength(this.aftBookAmount) >= 12)
        {
            log.debug("�v�Z�p���͕뉿���z���s���Ȓl");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02981,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v�Z�p���͕뉿���z���s���Ȓl�B");
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FeqBookPriceRegistResponse(this);
    }
}
@
