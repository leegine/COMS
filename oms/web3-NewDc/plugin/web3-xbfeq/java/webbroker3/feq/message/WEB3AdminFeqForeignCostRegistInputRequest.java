head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.25.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqForeignCostRegistInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO���������n�萔���o�^���̓��N�G�X�g(WEB3AdminFeqForeignCostRegistInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/20 �Ջ`�g (���u) �V�K�쐬
                 : 2005/08/03 �A�C��(���u) ���r���[       
Revesion History : 2008/11/12 ���m�a(���u) ���f��No.493
Revesion History : 2008/11/27 �đo�g(���u) ���f��No.498
*/

package webbroker3.feq.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BuySellTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.define.WEB3FeqCostDivDef;
import webbroker3.util.WEB3LogUtility;


/**
 * (�Ǘ��ҊO���������n�萔���o�^���̓��N�G�X�g)<BR>
 * �Ǘ��ҊO���������n�萔���o�^���̓��N�G�X�g�N���X
 *   
 * @@author �Ջ`�g
 * @@version 1.0
 */
public class WEB3AdminFeqForeignCostRegistInputRequest extends WEB3GenRequest 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminFeqForeignCostRegistInputRequest.class);

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_foreignCostRegistInput";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200507121517L;  
    
    /**
     * (�s��R�[�h)<BR>
     * �s��R�[�h
     */
    public String marketCode;
    
    /**
     * (�R�X�g�敪)<BR>
     * �R�X�g�敪<BR>
     * <BR>
     * 01�F���n�萔��<BR>
     * 02�F���n�����<BR>
     * 03�F���̑����n�R�X�g�P<BR>
     * 04�F���̑����n�R�X�g�Q
     */
    public String costDiv;

    /**
     * (�����敪)<BR>
     * �����敪<BR>
     * <BR>
     * �P�F����<BR>
     * �Q�F����
     */
    public String dealingType;

    /**
     * @@roseuid 42CE39FF0000
     */
    public WEB3AdminFeqForeignCostRegistInputRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�R�X�g�敪<BR>
     * <BR>
     *    �����N�G�X�g.�s��R�[�h != null �̏ꍇ�A�`�F�b�N���s���B
     *    this.�R�X�g�敪 != �i�h���n�萔���h or �h���n����Łh or <BR>
     * �h���̑����n�R�X�g�P�h or �h���̑����n�R�X�g�Q�h�j<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02030<BR>
     * <BR>
     * �Q�j�����敪<BR>
     * <BR>
     * �@@�����N�G�X�g.�s��R�[�h != null �̏ꍇ�A�`�F�b�N���s���B<BR>
     * �@@this.�����敪 != �i�h�����h or �h����h�j<BR>
     * �@@�@@�̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_01403<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42B0E62501EE
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);
        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        //�P�j�R�X�g�敪
        //   �����N�G�X�g.�s��R�[�h != null �̏ꍇ�A�`�F�b�N���s���B
        //   this.�R�X�g�敪 != �i�h���n�萔���h 
        //   or �h���n����Łh or �h���̑����n�R�X�g�P�h or �h���̑����n�R�X�g�Q�h�j
        //   �̏ꍇ�A��O���X���[����B

        if (marketCode != null &&
            (!WEB3FeqCostDivDef.FOREIGN_COMMISSION_FEE.equals(costDiv)
            && !WEB3FeqCostDivDef.FOREIGN_TAX.equals(costDiv)
            && !WEB3FeqCostDivDef.FOREIGN_FEE_EXT1.equals(costDiv)
            && !WEB3FeqCostDivDef.FOREIGN_FEE_EXT2.equals(costDiv))) 
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02030,
                this.getClass().getName() + STR_METHOD_NAME,
                " �R�X�g�敪���������`�F�b�N"); 
        }

        //�Q�j�����敪
        //   �����N�G�X�g.�s��R�[�h != null �̏ꍇ�A�`�F�b�N���s���B
        //   this.�����敪 != �i�h�����h or �h����h�j�̏ꍇ�A��O���X���[����B
        if (this.marketCode != null)
        {
            if (!WEB3BuySellTypeDef.BUY.equals(this.dealingType)
                && !WEB3BuySellTypeDef.SELL.equals(this.dealingType))
            {
                log.debug("�����敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01403,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
            }
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
        return new WEB3AdminFeqForeignCostRegistInputResponse(this);
    }
}
@
