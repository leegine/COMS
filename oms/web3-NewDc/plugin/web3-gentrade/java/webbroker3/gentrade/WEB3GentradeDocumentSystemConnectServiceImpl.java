head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeDocumentSystemConnectServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �h�L�������g�V�X�e���ڑ��T�[�r�XImpl(WEB3GentradeDocumentSystemConnectServiceImpl.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/07/28 � �� (���u) �쐬 
*/
package webbroker3.gentrade;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

/**
 * (�h�L�������g�V�X�e���ڑ��T�[�r�XImpl)<BR>
 * �h�L�������g�Ǘ��V�X�e���i�d�q���j�C���^�t�F�C�X�N���X<BR>
 */
public class WEB3GentradeDocumentSystemConnectServiceImpl
    implements WEB3GentradeDocumentSystemConnectService
{

    /**
     * @@roseuid 41075F4F0038
     */
    public WEB3GentradeDocumentSystemConnectServiceImpl()
    {

    }

    /**
     * (is�ژ_��������)<BR>
     * �ژ_�����d�q��t�ς��𔻒肷��B<BR>
     * �ژ_�����d�q��t�ςł����true�A�ȊO��false��ԋp����B<BR>
     * <BR>
     * �i�d�q��I/F�s���̂��߁A�ڍז���j<BR>
     * @@param l_genAccount - �ڋq�I�u�W�F�N�g
     * @@param l_productType - �����^�C�v
     * @@param l_strProductCode - �����R�[�h
     * @@return boolean
     * @@roseuid 40D7B7550049
     */
    public boolean isProspectusAccept(
        WEB3GentradeMainAccount l_genAccount,
        ProductTypeEnum l_productType,
        String l_strProductCode)
    {
        return true;
    }

    /**
     * (is��~��)<BR>
     * �d�q���V�X�e������~�����𔻒肷��B<BR>
     * �d�q���V�X�e������~���ł����true�A�ȊO��false��ԋp����B<BR>
     * <BR>
     * �i��~�I�y���[�V��������̂��߁A�ڍ�Pending�j<BR>
     * @@return boolean
     * @@roseuid 40F72B0E0122
     */
    public boolean isSystemStop()
    {
        return true;
    }
    
    /**
     * (is���{���) <BR>
     * �d�q���V�X�e�����{��Ђ��𔻒肷��B<BR>
     * �d�q�����{��Ђł����true�A�ȊO�� <BR>
     * false��ԋp����B<BR>
     * <BR>
     * (�ڍ�Pending)<BR>
     * <BR>
     * @@param l_genInstitution - �،����
     * @@return boolean
     */
    public boolean isEnforcementedInstitution(WEB3GentradeInstitution l_genInstitution)
    {
        return true;
    }

    /**
     * (is�����{) <BR>
     * ����񍐏����{���𔻒肷��B<BR>
     * <BR>
     * �ڋq������񍐏������{���Ă����<BR>
     * true�A�ȊO��false��ԋp����B<BR>
     * <BR>
     * @@param l_genMainAccount - �ڋq�I�u�W�F�N�g
     * @@return boolean
     */
    public boolean isTradingReportEnforcemented(WEB3GentradeMainAccount l_genMainAccount)
    {
        return true;
    }
}
@
