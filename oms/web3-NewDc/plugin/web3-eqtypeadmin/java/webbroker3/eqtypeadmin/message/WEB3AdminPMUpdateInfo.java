head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.44.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminPMUpdateInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �X�V��� (WEB3AdminPMUpdateInfo.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import java.util.HashMap;

import webbroker3.util.WEB3LogUtility;

/**
 * �i�X�V���j<BR>
 * <BR>
 * �X�V���N���X<BR>
 * ��AP�w�ł̂ݎg�p�B<BR>
 * <BR>
 * -------<English>-------------<BR>
 * <BR>
 * WEB3AdminPMUpdateInfo class<BR>
 * ��It is used only in AP layer<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminPMUpdateInfo
{
    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPMUpdateInfo.class);

    /**
     * �iID�j<BR>
     * <BR>
     * ID<BR>
     * <BR>
     * id<BR>
     * <BR>
     */
    public long id;

    /**
     * �i�L�����j<BR>
     * <BR>
     * �L����<BR>
     * ���X�V�Ώۂ��������updq�̏ꍇ�̓Z�b�g�B<BR>
     * <BR>
     * --------<English>-----------<BR>
     * <BR>
     * expirationDate<BR>
     * ��Set if eqtype_traded_product table is to be updated<BR>
     * <BR>
     */
    public String expirationDate = null;

    /**
     * �i�X�V��and�l�j<BR>
     * <BR>
     * key�F�X�V��Avalue�F�X�V�l��HashMap<BR>
     * <BR>
     * key: updateRow, value: HashMap of updateValue<BR>
     * <BR>
     */
    public HashMap updateRowAndValue;

    /**
     * @@roseuid 41FE05EF00CB
     */
    public WEB3AdminPMUpdateInfo()
    {
        updateRowAndValue = new HashMap();
    }

    /**
     * �i�X�V���j<BR>
     * <BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * Constructor<BR>
     * <BR>
     * @@return eqtypeadmin.message.UpdateInfo
     * @@roseuid 4190A84F0260
     */
    public WEB3AdminPMUpdateInfo updateInfo()
    {
        return new WEB3AdminPMUpdateInfo();
    }
}
@
