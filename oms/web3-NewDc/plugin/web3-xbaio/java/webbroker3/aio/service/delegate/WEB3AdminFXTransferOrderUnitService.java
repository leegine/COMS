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
Copyright        : ()ๅaค ุ\[VVXeๆ๑
File Name        : FXUึถUnitService(WEB3AdminFXTransferOrderUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/23 Aฟง(u) VK์ฌ
*/
package webbroker3.aio.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.aio.WEB3AdminFXTransferOrderUploadCsv;

/**
 * (FXUึถUnitService)<BR>
 * FXUึถUnitServiceC^[tF[X<BR>
 * 
 * @@author Aฟง
 * @@version 1.0
 */
public interface WEB3AdminFXTransferOrderUnitService extends Service 
{
    
    /**
     * Uึถ๐sคB <BR>
     * @@param l_fxTransferOrderUploadCsv - (FXUึถAbv[hCSV)<BR>
     * @@param l_intLineNumber - (sิ)<BR>
     * @@param l_strAdministratorCode - (วาR[h)<BR>
     * @@param l_institution - (ุ๏ะ)<BR>
     * @@param l_strPassword - (รุิ)<BR>
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
