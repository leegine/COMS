head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.47.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminProductExecutionInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���i���{���(WEB3ProductExecutionInfo.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.adminorderexecinquiry;

/**
 * (���i���{���)<BR>
 * <BR>
 * ���i�̎��{�����i�[����N���X<BR>
 * <BR>
 * WEB3AdminProductExecutionInfo<BR>
 * <BR>
 * It is a class to store execution information of a product<BR>
 * <BR>
 * @@author Amarnath
 * @@version 1.0
 */
public class WEB3AdminProductExecutionInfo
{
    /**
     * (���x�M�p���{�t���O)<BR>
     * <BR>
     * ���x�M�p�����{���Ă��邩�ǂ����̃t���O<BR>
     * <BR>
     * false�F�@@�����{<BR>
     * true�F�@@���{<BR>
     * <BR>
     * ----<English>----------------------<BR>
     * <BR>
     * marginSysFlag<BR>
     * <BR>
     * Flag to see if marginSys is executed<BR>
     * <BR>
     * false: FALSE<BR>
     * true: TRUE<BR>
     * <BR>
     */
    public boolean marginSysFlag = false;

    /**
     * (��ʐM�p���{�t���O)<BR>
     * <BR>
     * ��ʐM�p�����{���Ă��邩�ǂ����̃t���O<BR>
     * <BR>
     * false�F�@@�����{<BR>
     * true�F�@@���{<BR>
     * <BR>
     * ----<English>----------------------<BR>
     * <BR>
     * marginGenFlag<BR>
     * <BR>
     * Flag to see if marginGen is executed<BR>
     * <BR>
     * false: FALSE<BR>
     * true: TRUE<BR>
     * <BR>
     */
    public boolean marginGenFlag = false;

    /**
     * (�~�j�����{�t���O)<BR>
     * <BR>
     * �~�j�������{���Ă��邩�ǂ����̃t���O<BR>
     * <BR>
     * false�F�@@�����{<BR>
     * true�F�@@���{<BR>
     * <BR>
     * ----<English>----------------------<BR>
     * <BR>
     * miniFlag<BR>
     * <BR>
     * Flag to see if mini stock is executed<BR>
     * <BR>
     * false: FALSE<BR>
     * true: TRUE<BR>
     * <BR>
     */
    public boolean miniFlag = false;

    /**
     * (�I�v�V�������{�t���O)<BR>
     * <BR>
     * �I�v�V���������{���Ă��邩�ǂ����̃t���O<BR>
     * <BR>
     * false�F�@@�����{<BR>
     * true�F�@@���{<BR>
     * <BR>
     * ----<English>----------------------<BR>
     * <BR>
     * optionFlag<BR>
     * <BR>
     * Flag to see if option is executed<BR>
     * <BR>
     * false: FALSE<BR>
     * true: TRUE<BR>
     * <BR>
     */
    public boolean optionFlag = false;

    /**
     * (�敨���{�t���O)<BR>
     * <BR>
     * �敨�����{���Ă��邩�ǂ����̃t���O<BR>
     * <BR>
     * false�F�@@�����{<BR>
     * true�F�@@���{<BR>
     * <BR>
     * ----<English>----------------------<BR>
     * <BR>
     * futureFlag<BR>
     * <BR>
     * Flag to see if future is executed<BR>
     * <BR>
     * false: FALSE<BR>
     * true: TRUE<BR>
     * <BR>
     */
    public boolean futureFlag = false;

    /**
     * (���M���{�t���O)<BR>
     * <BR>
     * ���M�����{���Ă��邩�ǂ����̃t���O<BR>
     * <BR>
     * false�F�@@�����{<BR>
     * true�F�@@���{<BR>
     * <BR>
     * ----<English>----------------------<BR>
     * <BR>
     * mutualFlag<BR>
     * <BR>
     * Flag to see if mutual is executed<BR>
     * <BR>
     * false: FALSE<BR>
     * true: TRUE<BR>
     * <BR>
     */
    public boolean mutualFlag = false;

    /**
     * (�ݓ����{�t���O)<BR>
     * <BR>
     * �ݓ������{���Ă��邩�ǂ����̃t���O<BR>
     * <BR>
     * false�F�@@�����{<BR>
     * true�F�@@���{<BR>
     * <BR>
     * ----<English>----------------------<BR>
     * <BR>
     * ruitoFlag<BR>
     * <BR>
     * Flag to see if ruito is executed<BR>
     * <BR>
     * false: FALSE<BR>
     * true: TRUE<BR>
     * <BR>
     */
    public boolean ruitoFlag = false;

    /**
     * (�O���������{�t���O)<BR>
     * <BR>
     * �O�����������{���Ă��邩�ǂ����̃t���O<BR>
     * <BR>
     * false�F�@@�����{<BR>
     * true�F�@@���{<BR>
     * <BR>
     * ----<English>----------------------<BR>
     * <BR>
     * fstkFlag<BR>
     * <BR>
     * Flag to see if fstk is executed<BR>
     * <BR>
     * false: FALSE<BR>
     * true: TRUE<BR>
     * <BR>
     */
    public boolean fstkFlag = false;
    
    /**
     * @@roseuid 42130464020B
     */
    public WEB3AdminProductExecutionInfo()
    {
    }
}
@
