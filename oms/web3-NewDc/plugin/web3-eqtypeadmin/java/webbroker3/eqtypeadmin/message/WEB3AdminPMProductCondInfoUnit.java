head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminPMProductCondInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������Ɖ���(WEB3AdminPMProductCondInfoUnit.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.util.WEB3LogUtility;

/**
 * (���������Ɖ���)<BR>
 * <BR>
 * ���������Ɖ���N���X<BR>
 * <BR>
 * WEB3AdminPMProductCondInfoUnit<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminPMProductCondInfoUnit extends Message
{

    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminPMProductCondInfoUnit.class);

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
     * �i�����o�^���ꗗ�j
     * ----<English>--------------------
     * productRegistInfoList
     */
    public WEB3AdminPMProductRegistInfoUnit[] productRegistInfoList;

    /**
     * �i���������Ɖ���j<BR>
     * <BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * Constructor<BR>
     * <BR>
     * @@roseuid 41918404013C
     */
    public WEB3AdminPMProductCondInfoUnit()
    {

    }
}
@
