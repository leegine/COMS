head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.24.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcSortKeyUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �v�Z�T�[�r�X���v�v�Z���N�G�X�g(WEB3TrialCalcSortKeyUnit.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;

/**
 * �i�v�Z�T�[�r�X�\�[�g�L�[�j<BR>
 * <BR>
 * �v�Z�T�[�r�X�\�[�g�L�[�B�ꗗ�\�����̃\�[�g������N���X�B<BR>
 * <BR>
 * WEB3TrialCalcSortKeyUnit<BR>
 * @@author Prabhu
 * @@version 1.0
 */
public class WEB3TrialCalcSortKeyUnit extends Message
{
    /**
     * log WEB3LogUtility
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TrialCalcSortKeyUnit.class);

    /**
     * �i�L�[���ځj<BR>
     * <BR>
     * ���N���X�𗘗p�������N�G�X�g�΂��Ẵ��X�|���X�N���X���̃V���{�������L�[���ڂƂ�
     * ��B<BR>
     * �Ώۍ��ڂɂ��ẮA���N���X�𗘗p�������N�G�X�g��`���̋L�q���Q�ƁB<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * keyItem<BR>
     * The symbol name of the response class inside to the request using this class is
     * assumed to be a key item.  <BR>
     * Refer to the description defining the request using this class for the object
     * items. <BR>
     */
    public String keyItem;

    /**
     * �i�����^�~���j<BR>
     * <BR>
     * �����iasc�j�^�~���idesc�j�w��B<BR>
     * �iA�F�����@@D�F�~���j<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Ascending order (asc)/descending order (desc) specification . <BR>
     * (A: Ascending order D: Descending order)<BR>
     */
    public String ascDesc;

    /**
     * theWEB3TrialCalcPortfolioDisplayRequest WEB3TrialCalcPortfolioDisplayRequest
     */
    public WEB3TrialCalcPortfolioDisplayRequest theWEB3TrialCalcPortfolioDisplayRequest;

    /**
     * @@roseuid 41C81B0702EE
     */
    public WEB3TrialCalcSortKeyUnit()
    {

    }

    /**
     * ���N���X�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�jthis.�L�[���ځ�null�̏ꍇ�A<BR>
     * �@@�@@�u�\�[�g�L�[.�L�[���ڂ�null�v�̗�O��throw����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00085<BR>
     * <BR>
     * <BR>
     * �Q�jthis.�����^�~����null�̏ꍇ�A<BR>
     * �@@�@@�u�\�[�g�L�[.�����^�~����null�v�̗�O��throw����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00087<BR>
     * <BR>
     * <BR>
     * �R�jthis.�����^�~�������L�̍��ڈȊO�̏ꍇ�A<BR>
     * �@@�@@�u�\�[�g�L�[.�����^�~��������`�̒l�v�̗�O��throw����B<BR>
     * �@@�@@�@@�E�hA�F�����h<BR>
     * �@@�@@�@@�E�hD�F�~���h<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00088<BR>
     * <BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * The correspondence of this class is checked (However, it is assumed only when
     * the simple check concluded in this class). <BR>
     * <BR>
     * 1) If "this.keyItem = null", throw the following exception.<BR>
     *   [WEB3TrialCalcSortKeyUnit.keyItem is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00085<BR>
     * <BR>
     * <BR>
     * 2) If "this.ascDesc = null", throw the following exception<BR>
     *   [WEB3TrialCalcSortKeyUnit.ascDesc is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00087<BR>
     * <BR>
     * <BR>
     * 3) If "this.ascDesc" is not 'A'(Ascend) or 'D'(Descend) throw the following
     * exception<BR>
     *   [WEB3TrialCalcSortKeyUnit.ascDesc is an undefined value]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00088<BR>
     * <BR>
     * @@throws WEB3BaseException
     * �V�X�e�����ʁiweb3-common�j.(web3)�V�X�e�������N���X_common.WEB3BaseException
     * @@roseuid 4192F8E90050
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        if (this.keyItem == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        if (this.ascDesc == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        if (!WEB3AscDescDef.ASC.equals(this.ascDesc) && !WEB3AscDescDef.DESC.equals(this.ascDesc))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
