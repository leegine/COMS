head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.13.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AdminFXTransferOrderUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX�U�֒���UnitService(WEB3AdminFXTransferOrderUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/23 �A����(���u) �V�K�쐬
*/
package webbroker3.aio.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.aio.WEB3AdminFXTransferOrderUploadCsv;

/**
 * (FX�U�֒���UnitService)<BR>
 * FX�U�֒���UnitService�C���^�[�t�F�[�X<BR>
 * 
 * @@author �A����
 * @@version 1.0
 */
public interface WEB3AdminFXTransferOrderUnitService extends Service 
{
    
    /**
     * �U�֒����������s���B <BR>
     * @@param l_fxTransferOrderUploadCsv - (FX�U�֒����A�b�v���[�hCSV)<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@param l_strAdministratorCode - (�Ǘ��҃R�[�h)<BR>
     * @@param l_institution - (�،����)<BR>
     * @@param l_strPassword - (�Ïؔԍ�)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 43FBEDC00109
     */
    public WEB3GenResponse execute(
        WEB3AdminFXTransferOrderUploadCsv l_fxTransferOrderUploadCsv,
        int l_intLineNumber, 
        String l_strAdministratorCode,
        Institution l_institution,
        String l_strPassword) throws WEB3BaseException;
}
@
