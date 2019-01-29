head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.20.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMProductTradingStatusUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���i�ʎ戵��(WEB3AdminTMProductTradingStatusUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/10 �Ԑi�@@  (���u) ���f��No.110  
*/

package webbroker3.trademanagement.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.trademanagement.define.WEB3AdminTMProductRegistDivDef;

/**
 * �i���i�ʎ戵�󋵁j<BR>
 * <BR>
 * ���i�ʎ戵�󋵃N���X<BR>
 * <BR>
 * WEB3AdminTMProductTradingStatusUnit<BR>
 * <BR>
 * WEB3AdminTMProductTradingStatusUnit class<BR>
 * <BR>
 * @@author Prabhu
 * @@version 1.0
 */
public class WEB3AdminTMProductTradingStatusUnit extends Message
{
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTMProductTradingStatusUnit.class);
    /**
     * �i������t���i�j<BR>
     * ������t���i<BR>
     * <BR>
     * ������t�X�e�C�^�X�e�[�u��.������t���i�̒l<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * orderProduct<BR>
     * <BR>
     * order_accept_status.orderAcceptProduct<BR>
     */
    public String orderProduct;

    /**
     * �i������t�g�����U�N�V�����j<BR>
     * ������t�g�����U�N�V����<BR>
     * <BR>
     * ������t�X�e�C�^�X�e�[�u��.������t�g�����U�N�V�����̒l<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * order receipt transaction<BR>
     * <BR>
     * order_accept_status.orderAcceptTransaction<BR>
     */
    public String orderProductTran;

    /**
     * ��The latest DB data is set in the AP layer.<BR>
     * <BR>
     * �i�o�^�敪�j<BR>
     * <BR>
     * 0�F�@@�戵�\<BR>
     * 1�F�@@�o�b�`������<BR>
     * 2�F�@@��~<BR>
     * <BR>
     * ��AP�w�ōŐVDB�f�[�^���Z�b�g�B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * �iproductRegistDiv�j<BR>
     * <BR>
     * 0�F�@@Def.NORMAL<BR>
     * 1�F�@@Def.BATCH<BR>
     * 2�F�@@Def.SCRAM<BR>
     * <BR>
     */
    public String productRegistDiv;

    /**
     * �i�ύX��o�^�敪�j<BR>
     * �ύX��̓o�^�敪<BR>
     * <BR>
     * 0�F�@@�戵�\<BR>
     * 1�F�@@�o�b�`������<BR>
     * 2�F�@@��~<BR>
     * <BR>
     * ��PR�w�ł̓��͒l���Z�b�g�B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * �iafterProductRegistDiv�j<BR>
     * <BR>
     * 0�F�@@Def.NORMAL<BR>
     * 1�F�@@Def.BATCH<BR>
     * 2�F�@@Def.SCRAM<BR>
     * <BR>
     * ��The input value in PR layer is set. <BR>
     */
    public String afterProductRegistDiv = null;

    /**
     * �i�R���X�g���N�^�j<BR>
     * <BR>
     * Constructor<BR>
     * <BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 41735C96012D
     */
    public WEB3AdminTMProductTradingStatusUnit()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j������t���i�`�F�b�N<BR>
     * �@@�P�|�P�jthis.������t���i == null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u������t���i��null�v�̗�O���X���[����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01424<BR>
     * <BR>
     * �Q�j������t�g�����U�N�V�����`�F�b�N<BR>
     * �@@�Q�|�P�jthis.������t�g�����U�N�V���� == null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u������t�g�����U�N�V������null�v�̗�O���X���[����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01425<BR>
     * <BR>
     * �R�j�o�^�敪�`�F�b�N<BR>
     * �@@this.�o�^�敪 != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�R�|�P�jthis.�o�^�敪���ȉ��̒l�̂��Â�ɂ��Y�����Ȃ��ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�o�^�敪�G���[(����`�̒l)�v�̗�O���X���[����B<BR>
     * 0�F�@@�戵�\<BR>
     * 1�F�@@�o�b�`������<BR>
     * 2�F�@@��~<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00841<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * The correspondence of this class is checked<BR>
     * (However, it is assumed only when the simple check concluded in this class).
     * <BR>
     * <BR>
     * 1)orderProduct check<BR>
     *   1-1)If this.orderProduct == null<BR>
     *            Throw the following error [orderProduct is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01424<BR>
     * <BR>
     * 2)orderProductTran check<BR>
     *   2-1)If orderProductTran == null<BR>
     *            Throw the following error [orderProductTran is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01425<BR>
     * <BR>
     * 3)productRegistDiv check<BR>
     *   3-1)If this.productRegistDiv != null<BR>
     *     3-1-1)If this.productRegistDiv = 0 : Def.NORMAL  or<BR>
     *                  this.productRegistDiv = 1 : Def.BATCH  or<BR>
     *                  this.productRegistDiv = 2 : Def.SCRAM<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00841<BR>
     * @@roseuid 417383D000D7
     * @@throws WEB3BaseException WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // 1-1if orderProduct = null, throw Exception.
        if (this.orderProduct == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01424,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 2-1 orderProductTran = null, throw Exception.
        if (this.orderProductTran == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01425,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 3-1productRegistDiv != null, throw Exception.
        if (this.productRegistDiv != null)
        {
            // 3-1-1 if productRegistDiv != NORMAL, BATCH, SCRAM, throw Exception.
            if ((!WEB3AdminTMProductRegistDivDef.NORMAL.equals(this.productRegistDiv))
                && (!WEB3AdminTMProductRegistDivDef.BATCH.equals(this.productRegistDiv))
                && (!WEB3AdminTMProductRegistDivDef.SCRAM.equals(this.productRegistDiv)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00841,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
