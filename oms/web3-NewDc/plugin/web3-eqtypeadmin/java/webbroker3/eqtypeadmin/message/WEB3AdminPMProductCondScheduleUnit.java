head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminPMProductCondScheduleUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������\���� (WEB3AdminPMProductCondScheduleUnit.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import webbroker3.util.WEB3LogUtility;

/**
 * (���������\����)<BR>
 * <BR>
 * ���������\����N���X<BR>
 * <BR>
 * WEB3AdminPMProductCondScheduleUnit<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminPMProductCondScheduleUnit extends WEB3AdminPMProductCondConfigCommon
{
    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminPMProductCondScheduleUnit.class);

    /**
     * �i�����R�[�h�j<BR>
     * <BR>
     * �����R�[�h<BR>
     * <BR>
     * productCode<BR>
     * <BR>
     */
    public String productCode;

    /**
     * �i�������j<BR>
     * <BR>
     * ������<BR>
     * <BR>
     * productName<BR>
     * <BR>
     */
    public String productName;

    /**
     * �i�s��R�[�h�j<BR>
     * <BR>
     * �s��R�[�h<BR>
     * <BR>
     * 0�F�@@���̑�(�s�ꋤ��)<BR>
     * 1�F�@@����<BR>
     * 2�F�@@���<BR>
     * 3�F�@@���É�<BR>
     * 6�F�@@����<BR>
     * 8�F�@@�D�y<BR>
     * 9�F�@@NNM<BR>
     * 10�F�@@JASDAQ<BR>
     * <BR>
     * --------<English>-------------------<BR>
     * <BR>
     * MarketCode<BR>
     * <BR>
     * 0: Def.DEFAULT(common to markets)<BR>
     * 1: Def.TOKYO<BR>
     * 2: Def.OSAKA<BR>
     * 3: Def.NAGOYA<BR>
     * 6: Def.FUKUOKA<BR>
     * 8: Def.SAPPORO<BR>
     * 9: Def.NNM<BR>
     * 10: Def.JASDAQ<BR>
     * <BR>
     */
    public String marketCode;

    /**
     * �i�o�^�l�j<BR>
     * <BR>
     * �o�^�l(�\��)<BR>
     * <BR>
     * ����̏��A�區�ڋ敪�ȊO�́A�����񂪃Z�b�g�����B<BR>
     * <BR>
     * ����̏��A�區�ڋ敪�ɂ��ẮA<BR>
     * �u�y�⑫�����z�������������ݒ�o�^�l�R�[�h��`�v<BR>
     * �Q��<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * registData<BR>
     * <BR>
     * String is set  excluding specific smallItemDiv and largeItemDiv<BR>
     * <BR>
     * ��Set the latest DB data in AP layer<BR>
     * <BR>
     * Refer to "[Supplement] equity product condition regist data code def list" about
     * specific smallItemDiv and largeItemDiv<BR>
     * <BR>
     */
    public String registData;

    /**
     * �i�\��̓K�p����From�j<BR>
     * <BR>
     * �\��̓K�p����From<BR>
     * (YYYYMMDD)<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * applyStartDate<BR>
     * (YYYYMMDD)<BR>
     * <BR>
     */
    public String applyStartDate;

    /**
     * �i�\��̓K�p����To�j<BR>
     * <BR>
     * �\��̓K�p����To<BR>
     * (YYYYMMDD)<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * applyEndDate<BR>
     * (YYYYMMDD)<BR>
     * <BR>
     */
    public String applyEndDate;

	/**
	 * �i�X�V�҃R�[�h�j<BR>
	 * <BR>
	 * �X�V�҃R�[�h<BR>
	 */
	public String lastUpdater;

    /**
     * (�R���X�g���N�^)<BR>
     * <BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * Constructor<BR>
     * <BR>
     * @@roseuid 4194162E014A
     */
    public WEB3AdminPMProductCondScheduleUnit()
    {

    }
}
@
