head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminPMProductCondSettingUpdateInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����X�V��� (WEB3AdminPMProductCondSettingUpdateInfo.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import java.util.HashMap;
import java.util.ArrayList;

import webbroker3.util.WEB3LogUtility;

/**
 * �i���������ݒ�X�V���j<BR>
 * <BR><BR>
 * ���������ݒ�X�V���N���X
 * ��AP�w�ł̂ݎg�p<BR>
 * <BR>
 * -----<English>-----<BR>
 * <BR>
 * ProductCondSettingUpdateInfo class<BR>
 * ��It is used only in AP layer<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminPMProductCondSettingUpdateInfo
{
    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminPMProductCondSettingUpdateInfo.class);

    /**
     * �i�����X�V���j<BR>
     * <BR>
     * �����̍X�V���<BR>
     * <BR>
     * productUpdateInfo<BR>
     * <BR>
     */
    public WEB3AdminPMUpdateInfo productUpdateInfo = null;

    /**
     * �i���������X�V���j<BR>
     * <BR>
     * ���������̍X�V���<BR>
     * <BR>
     * equityProductUpdateInfo<BR>
     * <BR>
     */
    public WEB3AdminPMUpdateInfo equityProductUpdateInfo = null;

    /**
     * �i��������X�V���j<BR>
     * <BR>
     * ��������X�V���<BR>
     * <BR>
     * key�F�@@�s��R�[�h<BR>
     * value�F�@@�X�V���<BR>
     * <BR>
     * ----<English>----------<BR>
     * <BR>
     * tradeProductUpdateInfo<BR>
     * <BR>
     * key: productCode<BR>
     * value: updateInfo<BR>
     * <BR>
     */
    public HashMap tradeProductUpdateInfo;

    /**
     * �i�������(����)�X�V���j<BR>
     * <BR>
     * �������(����)�X�V���<BR>
     * <BR>
     * key�F�@@�s��R�[�h<BR>
     * value�F�@@�X�V���<BR>
     * <BR>
     * ----<English>-------<BR>
     * <BR>
     * tradeProductNextUpdateInfo<BR>
     * <BR>
     * key: marketCode<BR>
     * value: updateInfo<BR>
     * <BR>
     */
    public HashMap tradeProductNextUpdateInfo;

    /**
     * �i�������(���X��)�X�V���j<BR>
     * <BR>
     * �������(���X��)�X�V���<BR>
     * <BR>
     * key�F�@@�s��R�[�h<BR>
     * value�F�@@�X�V���<BR>
     * <BR>
     * ----<English>---------------<BR>
     * <BR>
     * tradeProductNext2UpdateInfo<BR>
     * <BR>
     * key: marketCode<BR>
     * value: updateInfo<BR>
     * <BR>
     */
    public HashMap tradeProductNext2UpdateInfo;

    /**
     * �i�\��X�V���j<BR>
     * <BR>
     * �X�V������������ݒ�Params���i�[����B<BR>
     * <BR>
     * Store eqtypeProductConditionParam to update<BR>
     * <BR>
     */
    public ArrayList scheduleUpdateInfo;

    /**
     * �i���������ݒ�X�V���j<BR>
     * <BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * Constructor<BR>
     * <BR>
     * @@roseuid 418AFEE4028A
     */
    public WEB3AdminPMProductCondSettingUpdateInfo()
    {

    }
}
@
