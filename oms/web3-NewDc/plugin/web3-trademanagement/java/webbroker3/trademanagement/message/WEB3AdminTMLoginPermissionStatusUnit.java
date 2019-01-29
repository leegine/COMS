head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.23.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMLoginPermissionStatusUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���O�C������(WEB3AdminTMLoginPermissionStatusUnit.java)
Author Name      : Daiwa Institute of Research
*/

package webbroker3.trademanagement.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.trademanagement.define.WEB3AdminTMLoginPermissionDivDef;

/**
 * �i���O�C�����󋵁j<BR>
 * <BR>
 * WEB3AdminTMLoginPermissionStatusUnit<BR>
 * <BR>
 * WEB3AdminTMLoginPermissionStatusUnit class<BR>
 * <BR>
 * @@author Prabhu
 * @@version 1.0
 */
public class WEB3AdminTMLoginPermissionStatusUnit extends Message
{
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTMLoginPermissionStatusUnit.class);
    /**
     * �i���X�R�[�h�j<BR>
     * <BR>
     * ���X�R�[�h<BR>
     * <BR>
     * branchCode<BR>
     */
    public String branchCode;

    /**
     * �i���X���j<BR>
     * <BR>
     * ���X��<BR>
     * <BR>
     * branchName<BR>
     */
    public String branchName;

    /**
     * �i���O�C�����敪�j<BR>
     * <BR>
     * 0�F�@@��~<BR>
     * 1�F�@@�\<BR>
     * <BR>
     * ��AP�w�ōŐVDB�f�[�^���Z�b�g�B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * �iloginPermissionDiv�j<BR>
     * <BR>
     * 0�F�@@Def.DISABLE<BR>
     * 1�F�@@Def.ENABLE <BR>
     * <BR>
     * ��The latest DB data is set in the AP layer.<BR>
     */
    public String loginPermissionDiv;

    /**
     * �i�ύX�ネ�O�C�����敪�j<BR>
     * �ύX��̃��O�C�����敪<BR>
     * <BR>
     * 0�F�@@��~<BR>
     * 1�F�@@�\<BR>
     * <BR>
     * ��PR�w�ł̓��͒l���Z�b�g�B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * �iafterLoginPermissionDiv�j<BR>
     * <BR>
     * 0 : Def.DISABLE<BR>
     * 1 : Def.ENABLE <BR>
     * <BR>
     * ��The input value in PR layer is set.<BR>
     */
    public String afterLoginPermissionDiv = null;

    /**
     * �R���X�g���N�^<BR>
     * <BR>
     * @@roseuid 41776D77037F
     */
    public WEB3AdminTMLoginPermissionStatusUnit()
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
     * �Q�j���O�C�����敪�`�F�b�N<BR>
     * �@@this.���O�C�����敪 != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�Q�|�P�jthis.���O�C�����敪���ȉ��̒l�̂��Â�ɂ��Y�����Ȃ��ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���O�C�����敪�G���[(����`�̒l)�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�E"0�F��~"<BR>
     * �@@�@@�@@�@@�@@�E"1�F�\"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01409<BR>
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
     * 2)loginPermissionDiv check<BR>
     *     2-1)If "this.loginPermissionDiv != null" and<BR>
     *              "this.loginPermissionDiv is not one of the following"<BR>
     *              Throw the following error [loginPermissionDiv error
     * (undefined)]<BR>
     *        (1)0 : Def.DISABLE<BR>
     *        (2)1 : Def.ENABLE<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01409<BR>
     * <BR>
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 41775E310295
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //  1-1when branchCode = null, throw Exception.
        if (this.branchCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 2-1loginPermissionDiv != null throw the Exception.
        if (this.loginPermissionDiv != null)
        {
            if ((!WEB3AdminTMLoginPermissionDivDef.DISABLE.equals(this.loginPermissionDiv))
                && (!WEB3AdminTMLoginPermissionDivDef.ENABLE.equals(this.loginPermissionDiv)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01409,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
