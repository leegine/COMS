head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeDocumentSystemConnectService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �h�L�������g�V�X�e���ڑ��T�[�r�X(WEB3GentradeDocumentSystemConnectService.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/07/28 � �� (���u) �쐬 
*/
package webbroker3.gentrade;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

/**
 * (�h�L�������g�V�X�e���ڑ��T�[�r�X)<BR>
 * �h�L�������g�Ǘ��V�X�e���i�d�q���j�C���^�t�F�C�X
 */
public interface WEB3GentradeDocumentSystemConnectService extends Service
{

    /**
     * (is�ژ_��������)<BR>
     * �ژ_�����d�q��t�ς��𔻒肷��B<BR>
     * @@param l_genMainAccount - �ڋq�I�u�W�F�N�g
     * @@param l_productType - �����^�C�v
     * @@param l_strProductCode - �����R�[�h
     * @@return boolean
     * @@roseuid 40F751F30180
     */
    public boolean isProspectusAccept(
        WEB3GentradeMainAccount l_genMainAccount,
        ProductTypeEnum l_productType,
        String l_strProductCode);

    /**
     * (is��~��) <BR>
     * �d�q���V�X�e������~�����𔻒肷��B <BR>
     * @@return boolean
     * @@roseuid 40F751EF0132
     */
    public boolean isSystemStop();

    /**
     * (is���{���) <BR>
     * �d�q���V�X�e�����{��Ђ��𔻒肷��B<BR>
     * @@param l_genInstitution - �،����
     * @@return boolean
     */
    public boolean isEnforcementedInstitution(WEB3GentradeInstitution l_genInstitution);

    /**
     * (is�����{) <BR>
     * ����񍐏����{���𔻒肷��B�B<BR>
     * @@param l_genMainAccount - �ڋq�I�u�W�F�N�g
     * @@return boolean
     */
    public boolean isTradingReportEnforcemented(WEB3GentradeMainAccount l_genMainAccount);
}
@
