head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.46.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	BookValueSpecDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradehistory.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.tradehistory.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link BookValueSpecDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link BookValueSpecRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
 * �N���C�A���g�R�[�h�ɂ����ĕK�v�Ƃ���鋤�ʂ̃f�[�^�I�y���[�V�������������Ă��܂��B 
 * <p> 
 *     <li> �V�������R�[�h�ɑ΂���ӂ̎�L�[�l�܂��̓I�u�W�F�N�g���쐬 </li> 
 *     <li> �O���L�[���烌�R�[�h������ </li> 
 *     <li> �O���L�[�̊֌W�ɂ���I�u�W�F�N�g������ </li> 
 *     <li> �C���f�b�N�X���������̃f�[�^�x�[�X�X�L�[�}�ɃN�G�������s </li> 
 * <p> 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.xtrade.kernel.data.DataAccessObject 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BookValueSpecPK 
 * @@see BookValueSpecRow 
 */
public class BookValueSpecDao extends DataAccessObject {


  /** 
   * ����{@@link BookValueSpecDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private BookValueSpecRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link BookValueSpecRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link BookValueSpecDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link BookValueSpecDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link BookValueSpecRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof BookValueSpecRow )
                return new BookValueSpecDao( (BookValueSpecRow) row );
            throw new java.lang.IllegalArgumentException( "Not a BookValueSpecRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link BookValueSpecRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link BookValueSpecRow}�I�u�W�F�N�g 
    */
    protected BookValueSpecDao( BookValueSpecRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link BookValueSpecRow}�I�u�W�F�N�g���擾���܂��B
   */
    public BookValueSpecRow getBookValueSpecRow() {
        return row;
    }


  /** 
   * �w���{@@link BookValueSpecRow}�I�u�W�F�N�g����{@@link BookValueSpecDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link BookValueSpecRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link BookValueSpecDao}�擾�̂��߂Ɏw���{@@link BookValueSpecRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link BookValueSpecDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static BookValueSpecDao forRow( BookValueSpecRow row ) throws java.lang.IllegalArgumentException {
        return (BookValueSpecDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link BookValueSpecRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link BookValueSpecRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link BookValueSpecPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link BookValueSpecParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( BookValueSpecRow.TYPE );
    }


  /** 
   * {@@link BookValueSpecRow}����ӂɓ��肷��{@@link BookValueSpecPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link BookValueSpecRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link BookValueSpecParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link BookValueSpecPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static BookValueSpecPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new BookValueSpecPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link BookValueSpecRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_bookValueSpecId �����Ώۂł���p_bookValueSpecId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link BookValueSpecRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static BookValueSpecRow findRowByPk( long p_bookValueSpecId ) throws DataFindException, DataQueryException, DataNetworkException {
        BookValueSpecPK pk = new BookValueSpecPK( p_bookValueSpecId );
        return findRowByPk( pk );
    }


  /** 
   * �w���BookValueSpecPK�I�u�W�F�N�g����{@@link BookValueSpecRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����BookValueSpecPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link BookValueSpecRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static BookValueSpecRow findRowByPk( BookValueSpecPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (BookValueSpecRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(BookValueSpecRow)}���g�p���Ă��������B 
   */
    public static BookValueSpecDao findDaoByPk( long p_bookValueSpecId ) throws DataFindException, DataQueryException, DataNetworkException {
        BookValueSpecPK pk = new BookValueSpecPK( p_bookValueSpecId );
        BookValueSpecRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(BookValueSpecPK)}�����{@@link #forRow(BookValueSpecRow)}���g�p���Ă��������B 
   */
    public static BookValueSpecDao findDaoByPk( BookValueSpecPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        BookValueSpecRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


      // (this object has no foreign keys)


    //===========================================================================
    //
    // Find Rows or Daos given index values
    //
    //===========================================================================

    //------------------------------------------------------
    // Find Rows given unique index values
    //------------------------------------------------------


  /** 
   * p_bookValueSpecId, and �ɂĎw��̒l�����ӂ�{@@link BookValueSpecRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_bookValueSpecId �����Ώۂł���p_bookValueSpecId�t�B�[���h�̒l
   * 
   * @@return �����w���p_bookValueSpecId, and �̒l�ƈ�v����{@@link BookValueSpecRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static BookValueSpecRow findRowByBookValueSpecId( long p_bookValueSpecId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            BookValueSpecRow.TYPE,
            "book_value_spec_id=?",
            null,
            new Object[] { new Long(p_bookValueSpecId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (BookValueSpecRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query BookValueSpecDao.findRowsByBookValueSpecId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByBookValueSpecId(long)}�����{@@link #forRow(BookValueSpecRow)}���g�p���Ă��������B 
   */
    public static BookValueSpecDao findDaoByBookValueSpecId( long p_bookValueSpecId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByBookValueSpecId( p_bookValueSpecId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_branchCode, p_accountCode, p_commodityCode, p_productCode, and �ɂĎw��̒l�Ɉ�v����{@@link BookValueSpecRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_commodityCode �����Ώۂł���p_commodityCode�t�B�[���h�̒l
   * @@param p_productCode �����Ώۂł���p_productCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_accountCode, p_commodityCode, p_productCode, and �̒l�ƈ�v����{@@link BookValueSpecRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionCodeBranchCodeAccountCodeCommodityCodeProductCode( String p_institutionCode, String p_branchCode, String p_accountCode, String p_commodityCode, String p_productCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BookValueSpecRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and commodity_code=? and product_code=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, p_commodityCode, p_productCode } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionCodeBranchCodeAccountCodeCommodityCodeProductCode(String, String, String, String, String)}�����{@@link #forRow(BookValueSpecRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionCodeBranchCodeAccountCodeCommodityCodeProductCode( String p_institutionCode, String p_branchCode, String p_accountCode, String p_commodityCode, String p_productCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeBranchCodeAccountCodeCommodityCodeProductCode( p_institutionCode, p_branchCode, p_accountCode, p_commodityCode, p_productCode ) );
    }

}
@
