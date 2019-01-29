head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.30.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqProductInfomationUpdateCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO�������������X�V���ʃ��N�G�X�g(WEB3AdminFeqProductInfomationUpdateCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/27 �A�C��(���u) �V�K�쐬
                 : 2005/08/02 �s�p(���u) ���r���[
*/

package webbroker3.feq.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.define.WEB3FeqBuySellStopDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ǘ��ҊO�������������X�V���ʃ��N�G�X�g)<BR>
 * �Ǘ��ҊO�������������X�V���ʃ��N�G�X�g�N���X
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminFeqProductInfomationUpdateCommonRequest extends WEB3GenRequest 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqProductInfomationUpdateCommonRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_feq_productInfomationUpdateCommon";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121526L;
        
    /**
     * (�����R�[�h)<BR>
     * �����R�[�h
     */
    public String productCode;
    
    /**
     * (�������i�����j)<BR>
     * �������i�����j
     */
    public String productNameKanji;
    
    /**
     * (���t��~�敪)<BR>
     * ���t��~�敪<BR>
     * <BR>
     * 0�F���t�\<BR>
     * 1�F���t��~
     */
    public String buyStopDiv;
    
    /**
     * (���t��~�敪)<BR>
     * ���t��~�敪<BR>
     * <BR>
     * 0�F���t�\<BR>
     * 1�F���t��~
     */
    public String sellStopDiv;
    
    /**
     * (���n�����R�[�h)<BR>
     * ��ʂɂē��͂��ꂽ���n�����R�[�h
     */
    public String localProductCode;
    
    /**
     * (���t�P��)<BR>
     * ��ʂɂē��͂��ꂽ���t�P��
     */
    public String buyUnit;
    
    /**
     * (�Œᔃ�t�P��)<BR>
     * ��ʂɂē��͂��ꂽ�Œᔃ�t�P��
     */
    public String minBuyUnit;
    
    /**
     * (���t�P��)<BR>
     * ��ʂɂē��͂��ꂽ���t�P��
     */
    public String sellUnit;
    
    /**
     * (�Œᔄ�t�P��)<BR>
     * ��ʂɂē��͂��ꂽ�Œᔄ�t�P��
     */
    public String minSellUnit;
    
    /**
     * @@roseuid 42CE39FA0290
     */
    public WEB3AdminFeqProductInfomationUpdateCommonRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�����R�[�h<BR>
     * <BR>
     * �P�|�P�j<BR>
     *    this.�����R�[�h == null<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00079<BR>
     * <BR>
     * �P�|�Q�j<BR>
     *    this.�����R�[�h.length() != 5<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00439<BR>
     * <BR>
     * �Q�j�������i�����j<BR>
     * <BR>
     *    this.�������i�����j�̃o�C�g�� > 50<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02050<BR>
     * <BR>
     * �R�j���t��~�敪<BR>
     * <BR>
     *    this.���t��~�敪 != �i�h���t�\�h or �h���t��~�h�j<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02051<BR>
     * <BR>
     * �S�j���t��~�敪<BR>
     * <BR>
     *    this.���t��~�敪 != �i�h���t�\�h or �h���t��~�h�j<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02052<BR>
     * <BR>
     * �T�j���n�����R�[�h<BR>
     * <BR>
     * �T�|�P�j<BR>
     *    this.���n�����R�[�h == null<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02053<BR>
     * <BR>
     * �T�|�Q�j<BR>
     *    this.���n�����R�[�h.length() > 9<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02054<BR>
     * <BR>
     * �T�|�R�j<BR>
     *    this.���n�����R�[�h != ����<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02055<BR>
     * <BR>
     * �U�j���t�P��<BR>
     * <BR>
     * �U�|�P�j<BR>
     *    this.���t�P�� == null<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02056<BR>
     * <BR>
     * �U�|�Q�j<BR>
     *    this.���t�P�� != ����<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02057<BR>
     * <BR>
     * �U�|�R�j<BR>
     *    this.���t�P��.length() > 7<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02058<BR>
     * <BR>
     * �U�|�S�j<BR>
     *    this.���t�P�� < 0<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02059<BR>
     * <BR>
     * �V�j�Œᔃ�t�P��<BR>
     * <BR>
     * �V�|�P�j<BR>
     *    this.�Œᔃ�t�P�� == null<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02060<BR>
     * <BR>
     * �V�|�Q�j<BR>
     *    this.�Œᔃ�t�P�� != ����<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02061<BR>
     * <BR>
     * �V�|�R�j<BR>
     *    this.�Œᔃ�t�P��.length() > 7<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02062<BR>
     * <BR>
     * �V�|�S�j<BR>
     *    this.�Œᔃ�t�P�� < 0<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02063<BR>
     * <BR>
     * �W�j���t�P��<BR>
     * <BR>
     * �W�|�P�j<BR>
     *    this.���t�P�� == null<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02064<BR>
     * <BR>
     * �W�|�Q�j<BR>
     *    this.���t�P�� != ����<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02065<BR>
     * <BR>
     * �W�|�R�j<BR>
     *    this.���t�P��.length() > 7<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02066<BR>
     * <BR>
     * �W�|�S�j<BR>
     *    this.���t�P�� < 0<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02067<BR>
     * <BR>
     * �X�j�Œᔄ�t�P��<BR>
     * <BR>
     * �X�|�P�j<BR>
     *    this.�Œᔄ�t�P�� == null<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02068<BR>
     * <BR>
     * �X�|�Q�j<BR>
     *    this.�Œᔄ�t�P�� != ����<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02069<BR>
     * <BR>
     * �X�|�R�j<BR>
     *    this.�Œᔄ�t�P��.length() > 7<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02070<BR>
     * <BR>
     * �X�|�S�j<BR>
     *    this.�Œᔄ�t�P�� < 0<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02071<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42B2B96D01E3
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�|�P�j
        //   this.�����R�[�h == null
        //   �̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.productCode))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                getClass().getName() + STR_METHOD_NAME,
                "�����R�[�h�������͂ł��B");
        }
        
        //�P�|�Q�j
        //   this.�����R�[�h.length() != 5
        //   �̏ꍇ�A��O���X���[����B
        if (this.productCode.length() != 5)
        {
            String l_strMessage = "�����R�[�h�̃T�C�Y���s���ł��B�u"+ this.productCode + "�v";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00439,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //�Q�j�������i�����j        
        //   this.�������i�����j�̃o�C�g�� > 50        
        //   �̏ꍇ�A��O���X���[����B
        if (this.productNameKanji != null
            && WEB3StringTypeUtility.getByteLength(this.productNameKanji) > 50)
        {
            String l_strMessage = "�������i�����j�̃T�C�Y���s���ł��B�u"+ this.productNameKanji + "�v";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02050,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        //�R�j���t��~�敪
        //   this.���t��~�敪 != �i�h���t�\�h or �h���t��~�i������K���j�@@or�h���t��~�i���ЋK���j�h�j
        //   �̏ꍇ�A��O���X���[����B
        if (!WEB3FeqBuySellStopDivDef.CAN.equals(this.buyStopDiv) 
            && !WEB3FeqBuySellStopDivDef.STOP_MARKET.equals(this.buyStopDiv)
            && !WEB3FeqBuySellStopDivDef.STOP_COMPANY.equals(this.buyStopDiv))
        {
            String l_strMessage = "���t��~�敪�̒l���s���ł��B�u"+ this.buyStopDiv + "�v";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02051,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //�S�j���t��~�敪
        //   this.���t��~�敪 != �i�h���t�\�h or �h���t��~�i������K���j�@@or�h���t��~�i���ЋK���j�h�j
        //   �̏ꍇ�A��O���X���[����B
        if (!WEB3FeqBuySellStopDivDef.CAN.equals(this.sellStopDiv) 
            && !WEB3FeqBuySellStopDivDef.STOP_MARKET.equals(this.sellStopDiv)
            && !WEB3FeqBuySellStopDivDef.STOP_COMPANY.equals(this.sellStopDiv))
        {
            String l_strMessage = "���t��~�敪�̒l���s���ł��B�u"+ this.sellStopDiv + "�v";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02052,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //�T�j���n�����R�[�h
        //�T�|�P�j
        //   this.���n�����R�[�h == null
        //   �̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.localProductCode))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02053,
                getClass().getName() + STR_METHOD_NAME,
                "���n�����R�[�h�������͂ł��B");
        }
        
        //�T�|�Q�j
        //   this.���n�����R�[�h.length() > 9
        //   �̏ꍇ�A��O���X���[����B
        if (this.localProductCode.length() > 9)
        {
            String l_strMessage = "���n�����R�[�h�̃T�C�Y���s���ł��B�u"+ this.localProductCode + "�v";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02054,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //�T�|�R�j
        //   this.���n�����R�[�h != ����
        //   �̏ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isDigit(this.localProductCode))
        {
            String l_strMessage = "���n�����R�[�h�����l�ȊO�̒l�ł��B�u"+ this.localProductCode + "�v";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02055,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //�U�j���t�P��
        //�U�|�P�j
        //   this.���t�P�� == null
        //   �̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.buyUnit))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02056,
                getClass().getName() + STR_METHOD_NAME,
                "���t�P�ʂ������͂ł��B");
        }
        
        //�U�|�Q�j
        //   this.���t�P�� != ����
        //   �̏ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isNumber(this.buyUnit))
        {
            String l_strMessage = "���t�P�ʂ����l�ȊO�̒l�ł��B�u" + this.buyUnit + "�v";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02057,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //�U�|�R�j
        //   this.���t�P��.length() > 7
        //   �̏ꍇ�A��O���X���[����B
        //�@@class: WEB3BusinessLayerException
        //�@@tag:   BUSINESS_ERROR_02058
        if (this.buyUnit.length() > 7)
        {
            String l_strMessage = "���t�P�ʂ̃T�C�Y���s���ł��B�u"+ this.buyUnit + "�v";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02058,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        //�U�|�S�j
        //   this.���t�P�� < 0        
        //   �̏ꍇ�A��O���X���[����B
        int l_intBuyUnit = Integer.parseInt(this.buyUnit);
        if (l_intBuyUnit < 0)
        {
            String l_strMessage = "���t�P�ʂ��}�C�i�X�̒l�ł��B�u"+ this.buyUnit + "�v";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02059,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //�V�j�Œᔃ�t�P��
        //�V�|�P�j
        //   this.�Œᔃ�t�P�� == null
        //   �̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.minBuyUnit))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02060,
                getClass().getName() + STR_METHOD_NAME,
                "�Œᔃ�t�P�ʂ������͂ł��B");
        }
        
        //�V�|�Q�j
        //   this.�Œᔃ�t�P�� != ����        
        //   �̏ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isNumber(this.minBuyUnit))
        {
            String l_strMessage = "�Œᔃ�t�P�ʂ����l�ȊO�̒l�ł��B�u" + this.minBuyUnit + "�v";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02061,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //�V�|�R�j
        //   this.�Œᔃ�t�P��.length() > 7
        //   �̏ꍇ�A��O���X���[����B
        if (this.minBuyUnit.length() > 7)
        {
            String l_strMessage = "�Œᔃ�t�P�ʂ̃T�C�Y���s���ł��B�u"+ this.minBuyUnit + "�v";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02062,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //�V�|�S�j
        //   this.�Œᔃ�t�P�� < 0
        //   �̏ꍇ�A��O���X���[����B
        int l_intMinBuyUnit = Integer.parseInt(this.minBuyUnit);
        if (l_intMinBuyUnit < 0)
        {
            String l_strMessage = "�Œᔃ�t�P�ʂ��}�C�i�X�̒l�ł��B�u"+ this.minBuyUnit + "�v";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02063,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //�W�j���t�P��
        //�W�|�P�j
        //   this.���t�P�� == null
        //   �̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.sellUnit))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02064,
                getClass().getName() + STR_METHOD_NAME,
                "���t�P�ʂ������͂ł��B");
        }
        
        //�W�|�Q�j
        //   this.���t�P�� != ����
        //   �̏ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isNumber(this.sellUnit))
        {
            String l_strMessage = "���t�P�ʂ����l�ȊO�̒l�ł��B�u" + this.sellUnit + "�v";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02065,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //�W�|�R�j
        //   this.���t�P��.length() > 7
        //   �̏ꍇ�A��O���X���[����B
        if (this.sellUnit.length() > 7)
        {
            String l_strMessage = "���t�P�ʂ̃T�C�Y���s���ł��B�u"+ this.sellUnit + "�v";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02066,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //�W�|�S�j
        //   this.���t�P�� < 0
        //   �̏ꍇ�A��O���X���[����B
        int l_intSellUnit = Integer.parseInt(this.sellUnit);
        if (l_intSellUnit < 0)
        {
            String l_strMessage = "���t�P�ʂ��}�C�i�X�̒l�ł��B�u"+ this.sellUnit + "�v";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02067,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //�X�j�Œᔄ�t�P��
        //�X�|�P�j
        //   this.�Œᔄ�t�P�� == null
        //   �̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.minSellUnit))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02068,
                getClass().getName() + STR_METHOD_NAME,
                "�Œᔄ�t�P�ʂ������͂ł��B");
        }
        
        //�X�|�Q�j
        //   this.�Œᔄ�t�P�� != ����
        //   �̏ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isNumber(this.minSellUnit))
        {
            String l_strMessage = "�Œᔄ�t�P�ʂ����l�ȊO�̒l�ł��B�u" + this.minSellUnit + "�v";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02069,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //�X�|�R�j
        //   this.�Œᔄ�t�P��.length() > 7
        //   �̏ꍇ�A��O���X���[����B
        if (this.minSellUnit.length() > 7)
        {
            String l_strMessage = "�Œᔄ�t�P�ʂ̃T�C�Y���s���ł��B�u"+ this.minSellUnit + "�v";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02070,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //�X�|�S�j
        //   this.�Œᔄ�t�P�� < 0
        //   �̏ꍇ�A��O���X���[����B
        int l_intMinSellUnit = Integer.parseInt(this.minSellUnit);
        if (l_intMinSellUnit < 0)
        {
            String l_strMessage = "�Œᔄ�t�P�ʂ��}�C�i�X�̒l�ł��B�u"+ this.minSellUnit + "�v";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02071,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
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
        return null;
    }
}@
