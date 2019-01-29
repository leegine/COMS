head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.35.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqSellCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O���������t���ʃ��N�G�X�g(WEB3FeqSellCommonRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ���� (���u) �V�K�쐬
                 : 2005/08/01 �s�p(���u) ���r���[   
*/

package webbroker3.feq.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3InputOutputActionSettlementDivDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O���������t���ʃ��N�G�X�g)<BR>
 * �O���������t���ʃ��N�G�X�g�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3FeqSellCommonRequest extends WEB3FeqCommonRequest 
{    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "feq_sellCommon";

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
     * (���ϋ敪)<BR>
     * ���ϋ敪<BR>
     * <BR>
     * 0�F�~��<BR>
     * 1�F�O��<BR>
     */
    public String settleDiv;
    
    /**
     * @@roseuid 42CE3A0A0138
     */
    public WEB3FeqSellCommonRequest() 
    {
     
    }
    
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqSellCommonRequest.class);
        
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�X�[�p�[�N���X��validate()���\�b�h���Ăяo���B<BR>
     * <BR>
     * �Q�j�ۗL���YID�`�F�b�N<BR>
     *    this.�ۗL���YID == null<BR>
     * <BR>
     *    �̏ꍇ�A�u�ۗL���YID��null�v�̗�O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_01919<BR>
     * <BR>
     * �R�j���ϋ敪�`�F�b�N<BR>
     * <BR>
     * �R�|�P�j<BR>
     *    this.���ϋ敪 == null<BR>
     * <BR>
     *    �̏ꍇ�A�u���ϋ敪��null�v�̗�O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02111<BR>
     * <BR>
     * �S�|�Q�j<BR>
     *    this.���ϋ敪 != �i�h�~�݁h or �h�O�݁h�j<BR>
     * <BR>
     *    �̏ꍇ�A�u���ϋ敪������`�̒l�v�̗�O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02112<BR>
     * 
     * @@throws WEB3BaseException
     * @@roseuid 4294783B009C
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�X�[�p�[�N���X��validate()���\�b�h���Ăяo���B
        super.validate();
        
        //�Q�j�ۗL���YID�`�F�b�N 
        //this.�ۗL���YID == null
        //�̏ꍇ�A�u�ۗL���YID��null�v�̗�O���X���[����B
        if (this.assetId == null)
        {
            log.debug("�ۗL���YID�����w��(null)�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01919,
                this.getClass().getName() + STR_METHOD_NAME,
                "�ۗL���YID�����w��(null)�ł��B" + this.assetId);
        }
        
        //�R�j���ϋ敪�`�F�b�N
        //�R�|�P�jthis.���ϋ敪 == null
        //�̏ꍇ�A�u���ϋ敪��null�v�̗�O���X���[����B
        if (this.settleDiv == null)
        {
            log.debug("���ϋ敪��null�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02111,
                this.getClass().getName() + STR_METHOD_NAME,
                "���ϋ敪��null�ł��B" + this.settleDiv);
        }
        
        //�S�|�Q�jthis.���ϋ敪 != �i�h�~�݁h or �h�O�݁h�j
        //�̏ꍇ�A�u���ϋ敪������`�̒l�v�̗�O���X���[����B
        if (!WEB3InputOutputActionSettlementDivDef.EN_SETTLE.equals(this.settleDiv)
            && !WEB3InputOutputActionSettlementDivDef.FOREIGN_SETTLE.equals(this.settleDiv))
        {
            log.debug("���ϋ敪������`�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02112,
                this.getClass().getName() + STR_METHOD_NAME,
                "���ϋ敪������`�̒l�ł��B" + this.settleDiv);
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
