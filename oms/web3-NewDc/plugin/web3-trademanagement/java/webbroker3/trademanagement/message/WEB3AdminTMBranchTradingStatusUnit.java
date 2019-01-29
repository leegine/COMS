head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.23.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMBranchTradingStatusUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���X�ʎ戵��(WEB3AdminTMBranchTradingStatusUnit.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trademanagement.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;

/**
 * �i���X�ʎ戵�󋵁j<BR>
 * <BR>
 * ���X�ʎ戵�󋵃N���X<BR>
 * <BR>
 * WEB3AdminTMBranchTradingStatusUnit<BR>
 * <BR>
 * WEB3AdminTMBranchTradingStatusUnit class<BR>
 * @@author Prabhu
 * @@version 1.0
 */
public class WEB3AdminTMBranchTradingStatusUnit extends Message
{
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTMBranchTradingStatusUnit.class);

    /**
     * �i���X�R�[�h�j<BR>
     * <BR>
     * ���X�R�[�h<BR>
     * <BR>
     * branchCode<BR>
     */
    public String branchCode;

    /**
     * productTradingStatus
     */
    public WEB3AdminTMProductTradingStatusUnit[] productTradingStatus;

    /**
     * �i�R���X�g���N�^�j<BR>
     * <BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * @@roseuid 41776D5700B0
     */
    public WEB3AdminTMBranchTradingStatusUnit()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j���X�R�[�h�`�F�b�N<BR>
     * �@@�P�|�P�jthis.���X�R�[�h == null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���X�R�[�h��null�v�̗�O���X���[����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00833<BR>
     * <BR>
     * �Q�j���i�ʎ戵�󋵈ꗗ�`�F�b�N<BR>
     * �@@�Q�|�P�jthis.���i�ʎ戵�󋵈ꗗ == null�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�u���i�ʎ戵�󋵈ꗗ��null�v�̗�O���X���[����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01407<BR>
     * <BR>
     * �@@�Q�|�Q�jthis.���i�ʎ戵�󋵈ꗗ.length == 0�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�u���i�ʎ戵�󋵈ꗗ�̗v�f����0�v�̗�O���X���[����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01408<BR>
     * <BR>
     * �@@�Q�|�R�jthis.���i�ʎ戵�󋵈ꗗ�̗v�f�����ȉ��̏�����<BR>
     * �@@�@@�@@�@@�@@�J��Ԃ��B<BR>
     * �@@�@@�@@�@@�@@�Q�|�R�|�P�j���i�ʎ戵��.validate()���R�[������B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * The correspondence of this class is checked<BR>
     * (However, it is assumed only when the simple check concluded in this class).
     * <BR>
     * <BR>
     * 1)branchCode check<BR>
     *   1-1)If this.branchCode == null<BR>
     *           Throw the following error [branchCode is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00833<BR>
     * <BR>
     * 2)branchTradingStatusList check<BR>
     *   2-1)If branchTradingStatusList == null<BR>
     *           Throw the following error [branchTradingStatusList is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01407<BR>
     * <BR>
     *   2-2)If this.branchTradingStatusList.length == 0<BR>
     *           Throw the following error [branchTradingtStatusList has 0
     * elements]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01408<BR>
     * <BR>
     *   2-3)Loop for as many elements there are in branchTradingStatusList<BR>
     *     2-3-1)Call WEB3AdminTMProductTradingStatusUnit.validate()<BR>
     * <BR>
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 4177590E018B
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        int l_productTradingStatusListlength;
        WEB3AdminTMProductTradingStatusUnit l_productTradingStatus = null;

        // 1-1when branchCode = null.
        if (this.branchCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 2-1 productTradingStatus = null, throw Exception
        if (this.productTradingStatus == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01407,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            l_productTradingStatusListlength = this.productTradingStatus.length;

            // 2-2 l_productTradingStatusListlength = 0, throw Exception
            if (l_productTradingStatusListlength == 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01408,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            // 2-3 Loop for as many elements there are in branchTradingStatusList
            for (int i = 0; i < l_productTradingStatusListlength; i++)
            {
                l_productTradingStatus = this.productTradingStatus[i];
                // 2-3-1 call validate()
                l_productTradingStatus.validate();
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
